package com.natali_pi.home_money.add_spending;

import android.text.style.TtsSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Money;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.models.SpendingComponent;
import com.natali_pi.home_money.utils.PickDateDialog;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class SpendingFragment extends BaseFragment{
    EditText name;
    EditText date;
    @Override
    protected void resolveDaggerDependencies() {

    }

    @Override
    protected int contentView() {
        return R.layout.fragment_add_spending;
    }
ComponentsAdapter componentsAdapter;
    PickDateDialog pickDateDialog;
    @Override
    protected View onCreateView(View root) {
         name = (EditText) root.findViewById(R.id.name);
         date = (EditText) root.findViewById(R.id.date);
         date.setKeyListener( null);
         date.setOnClickListener(pickDateDialog = new PickDateDialog(getActivity()));
         date.setText(pickDateDialog.getText());
        LinearLayout addComponent = (LinearLayout) root.findViewById(R.id.addComponent);
        final ListView componentsHolder = (ListView) root.findViewById(R.id.componentsHolder);
        componentsAdapter = new ComponentsAdapter(null, getActivity());
       componentsHolder.setAdapter(componentsAdapter);
        addComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                componentsAdapter.addSpendingComponent();
            }
        });


        return root;
    }

    public Spending getSpending(){
        Spending spending = new Spending(name.getText().toString());
        spending.setDate(pickDateDialog.getText());
        spending.setComponents(componentsAdapter.getComponents());
    return spending;
    }
    private Money getSum(){
        Money result = new Money();
        for (SpendingComponent spendingComponent : componentsAdapter.getComponents()) {
            Money.sum(result, spendingComponent.getPrice());
        }
        return result;
    }
}
