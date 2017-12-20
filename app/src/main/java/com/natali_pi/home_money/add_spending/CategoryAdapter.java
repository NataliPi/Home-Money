package com.natali_pi.home_money.add_spending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.utils.App;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Natali-Pi on 24.11.2017.
 */

public class CategoryAdapter extends BaseAdapter {

    private ArrayList<Category> categories;
    private ArrayList<RelativeLayout> holders = new ArrayList<>();
    private Context context;
    private OnChooseListener listener;

    public CategoryAdapter(ArrayList<Category> categories, Context context, OnChooseListener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return categories.size() + 1;
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_category, null);
        }
        RelativeLayout holder = (RelativeLayout) view.findViewById(R.id.holder);
        TextView text = (TextView) view.findViewById(R.id.text);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        holders.add(position, holder);
        if (position < categories.size()) {
            Picasso.with(context).load(App.BASE_URL + App.PICTURE_URL + categories.get(position).getPhoto()).into(icon);
            text.setText(categories.get(position).getName());
            holder.setOnClickListener((v) -> {
                clearBackgrounds();
                holder.setBackgroundResource(R.color.yellow);
                listener.choosen(categories.get(position));
            });
        } else {
            text.setText(R.string.add);
            icon.setImageResource(R.drawable.plus);
            holder.setOnClickListener((v) -> {
                clearBackgrounds();
                holder.setBackgroundResource(R.color.yellow);
                listener.choosen(null);
            });
        }

        return view;
    }

    public void clearBackgrounds() {
        for (RelativeLayout holder : holders) {
            holder.setBackgroundResource(R.color.white);
        }
    }

    public interface OnChooseListener {
        void choosen(Category category);
    }
}
