package com.natali_pi.home_money.add_spending;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Money;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.utils.Currency;
import com.natali_pi.home_money.utils.DataBase;
import com.natali_pi.home_money.utils.views.DropdownView;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class SpendingFragment extends BaseFragment {
    private ComponentsAdapter componentsAdapter;
    private EditText name;
    private DropdownView date;
    private SpendingPresenter presenter = SpendingPresenter.getInstance();
    private DropdownView currency;
    private EditText price;
    boolean calculateSumm = true;

    @Override
    protected void resolveDaggerDependencies() {

    }

    @Override
    protected int contentView() {
        return R.layout.fragment_add_spending;
    }


    @Override
    protected View onCreateView(View root) {
        ImageView spendPhoto = (ImageView) root.findViewById(R.id.spendingPhoto);
        spendPhoto.setOnClickListener((v) -> {
            PickImageDialog.build(getBaseActivity().getPickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult result) {
                            spendPhoto.setImageBitmap(new CropCircleTransformation().transform(result.getBitmap()));
                            presenter.setSpendingPicture(result.getBitmap());
                        }
                    }).show(getBaseActivity());
        });

        name = (EditText) root.findViewById(R.id.name);
        price = (EditText) root.findViewById(R.id.price);
        currency = (DropdownView) root.findViewById(R.id.currency);
        currency.setItems(Currency.getAsList());
        currency.setDefaultFirst(DataBase.getInstance().getCurrentCurrency());

        date = (DropdownView) root.findViewById(R.id.date);
        date.setTodayDate();
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    calculateSumm = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        price.setOnFocusChangeListener((v, d) -> {
            if (d) {
                calculateSumm = false;
            }
        });
        LinearLayout addComponent = (LinearLayout) root.findViewById(R.id.addComponent);

        final ListView componentsHolder = (ListView) root.findViewById(R.id.componentsHolder);
        componentsAdapter = new ComponentsAdapter(null, getActivity());
        componentsHolder.setAdapter(componentsAdapter);
        componentsAdapter.setSummListener((summ) -> {
            if (calculateSumm) {
                price.setText(summ.toString());
            }
        });
        addComponent.setOnClickListener((View view) -> {
            componentsAdapter.addSpendingComponent();
        });


        return root;
    }

    public Spending getSpending() {
        Spending spending = new Spending(name.getText().toString());
        spending.setDate(date.getData());
        if(!calculateSumm) {
            spending.setSumm(new Money(price.getText().toString(), currency.getData()));
        }
        spending.setComponents(componentsAdapter.getComponents());
        return spending;
    }


}
