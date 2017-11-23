package com.natali_pi.home_money.add_spending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.SpendingComponent;

import java.util.List;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class ComponentsAdapter extends BaseAdapter {
    List<SpendingComponent> components;
    Context context;

    public ComponentsAdapter(List<SpendingComponent> components, Context context) {
        this.components = components;
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
        TextView spendedName = (TextView) view.findViewById(R.id.spendedName);
        spendedName.setText(components.get(position).getName());
        TextView price = (TextView) view.findViewById(R.id.price);
        price.setText(components.get(position).getPrice().toString());
        TextView currency = (TextView) view.findViewById(R.id.currency);
        currency.setText(components.get(position).getPrice().getCurrency().toString());
        return view;
    }
}
