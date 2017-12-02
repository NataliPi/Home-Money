package com.natali_pi.home_money.add_spending;

import android.view.View;
import android.widget.ListView;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Category;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 24.11.2017.
 */

public class CategoryFragment extends BaseFragment {

    @Override
    protected void resolveDaggerDependencies() {

    }

    @Override
    protected int contentView() {
        return R.layout.fragment_category;
    }

    @Override
    protected View onCreateView(View root) {
        ListView list = (ListView) root.findViewById(R.id.list);
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("beauty"));
        categories.add(new Category("education"));
        categories.add(new Category("entertainment"));
        categories.add(new Category("food"));
        categories.add(new Category("health"));
        categories.add(new Category("home"));
        categories.add(new Category("pets"));
        categories.add(new Category("shopping"));
        categories.add(new Category("sport"));
        categories.add(new Category("transport"));
        categories.add(new Category("travel"));
        list.setAdapter(new CategoryAdapter(categories, getActivity()));
        return root;
    }
}
