package com.natali_pi.home_money.add_spending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Category;

import java.util.ArrayList;


/**
 * Created by Natali-Pi on 24.11.2017.
 */

public class CategoryAdapter extends BaseAdapter {

    private ArrayList<Category> categories;
    private Context context;

    public CategoryAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categories.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (position < categories.size()) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.itom_category, null);
            }
            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText(categories.get(position).getName());
        } else {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.itom_category, null);
            }
            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText("Жопа");
        }
        return view;
    }
}
