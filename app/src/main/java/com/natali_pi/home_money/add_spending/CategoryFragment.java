package com.natali_pi.home_money.add_spending;

import android.text.InputType;
import android.view.View;
import android.widget.ListView;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.login.LoginActivity;
import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.utils.DataBase;
import com.natali_pi.home_money.utils.OneButtonDialog;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 24.11.2017.
 */

public class CategoryFragment extends BaseFragment {
    ListView list;
    SpendingPresenter presenter;

    public void setPresenter(SpendingPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void resolveDaggerDependencies() {

    }

    @Override
    protected int contentView() {
        return R.layout.fragment_category;
    }

    @Override
    protected View onCreateView(View root) {
        list = (ListView) root.findViewById(R.id.list);
        ArrayList<Category> categories = DataBase.getInstance().getFamily().getCategories();
            setAdapter(categories);
        return root;
    }

    public void setAdapter(ArrayList<Category> categories) {
        list.setAdapter(new CategoryAdapter(categories, getActivity(), (category) -> {
            if(category !=  null) {
                presenter.setCategory(category);
            } else {
                new OneButtonDialog(getActivity(), OneButtonDialog.DIALOG_TYPE.INPUT_ONLY)
                        .setTitle("Добавление категории")
                        .setEditTextHint("Введите название")
                        .setOkListener((categoryName)->{
                            presenter.setCategory(new Category(categoryName));
                        })
                        .build();
            }
        }));
        Spending spending;
        if(getArguments() != null && (spending = (Spending)getArguments().getSerializable(DATA)) != null){
                ((CategoryAdapter) list.getAdapter()).setCategory(spending.getCategory());
            }
        
    }
    /*private String id;
    public void setCategory(String id){
        this.id = id;
    }*/

}
