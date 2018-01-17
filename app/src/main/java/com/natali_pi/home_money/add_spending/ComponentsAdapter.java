package com.natali_pi.home_money.add_spending;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Money;
import com.natali_pi.home_money.models.SpendingComponent;
import com.natali_pi.home_money.utils.Currency;
import com.natali_pi.home_money.utils.DataBase;
import com.natali_pi.home_money.utils.TextPickerDialog;
import com.natali_pi.home_money.utils.views.DropdownView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class ComponentsAdapter extends BaseAdapter {
    private List<SpendingComponent> components = new ArrayList<>();
    private Context context;
    private boolean isChangable;
    private TextPickerDialog dialog = null;

    public ComponentsAdapter(Context context, List<SpendingComponent> components, boolean isChangable) {
        this.context = context;
        this.isChangable = isChangable;
        if (components != null) {
            this.components.addAll(components);
        }

    }

    @Override
    public int getCount() {
        return this.components.size();
    }

    @Override
    public Object getItem(int position) {
        return this.components.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_component, null);
        }

        EditText spendedName = (EditText) view.findViewById(R.id.spendedName);
        spendedName.setText(components.get(position).getName());

        EditText price = (EditText) view.findViewById(R.id.price);
        price.setText(components.get(position).getPrice().toString());
        DropdownView currency = view.findViewById(R.id.currency);
        currency.setItems(Currency.getAsList());
        currency.setDefaultFirst(DataBase.getInstance().getCurrentCurrency());
if(isChangable) {
    spendedName.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            components.get(position).setName("" + charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                components.get(position).setPrice(new Money(price.getText().toString(), currency.getData()));
                summListener.onSummChanged(getSumm());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
} else {
    spendedName.setEnabled(false);
    price.setEnabled(false);
    currency.setEnabled(false);
}
        return view;
    }

    private OnSummChangedListener summListener;

    public void setSummListener(OnSummChangedListener summListener) {
        this.summListener = summListener;
    }

    interface OnSummChangedListener {
        void onSummChanged(Money summ);
    }

    private Money getSumm() {
        Money summ = new Money(0.0f);
        for (SpendingComponent component : components) {
            summ = Money.sum(summ, component.getPrice());
        }
        return summ;
    }

    public List<SpendingComponent> getComponents() {
        return components;
    }

    public void addSpendingComponent() {
        components.add(new SpendingComponent());
        notifyDataSetChanged();
    }
}
