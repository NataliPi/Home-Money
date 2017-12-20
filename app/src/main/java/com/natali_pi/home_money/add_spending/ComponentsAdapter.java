package com.natali_pi.home_money.add_spending;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.SpendingComponent;
import com.natali_pi.home_money.utils.Currency;
import com.natali_pi.home_money.utils.TextPickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class ComponentsAdapter extends BaseAdapter {
    List<SpendingComponent> components = new ArrayList<>();
    Context context;
    TextPickerDialog dialog = null;
    public ComponentsAdapter(List<SpendingComponent> components, Context context) {
        if(components != null) {
            this.components.addAll(components);
        }
        this.context = context;
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
        TextView currency = (TextView) view.findViewById(R.id.currency);
        currency.setText(components.get(position).getPrice().getCurrency().toString());
        currency.setOnClickListener((v)->{
            dialog = new TextPickerDialog(context, "Выберите валюту",0, Currency.getAsList())
                    .setOnDoneListener((result)->{
                currency.setText(dialog.getInnerResultString(result));
                        components.get(position).getPrice().setCurrency(dialog.getInnerResultString(result));
            }).showMe();
        });
        spendedName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                components.get(position).setName(""+charSequence);
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
                components.get(position).setPrice(price.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    public List<SpendingComponent> getComponents() {
        return components;
    }
    public void addSpendingComponent(){
        components.add(new SpendingComponent());
        notifyDataSetChanged();
    }
}
