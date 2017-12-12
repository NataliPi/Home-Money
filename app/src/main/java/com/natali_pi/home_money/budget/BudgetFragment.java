package com.natali_pi.home_money.budget;

import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.add_spending.CategoryAdapter;
import com.natali_pi.home_money.login.LoginActivity;
import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.utils.OneButtonDialog;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 10.12.2017.
 */

public class BudgetFragment  extends BaseFragment {

    @Override
    protected void resolveDaggerDependencies() {

    }

    @Override
    protected int contentView() {
        return R.layout.fragment_budget;
    }

    @Override
    protected View onCreateView(View root) {
        ImageView pen = (ImageView) root.findViewById(R.id.pen);
        pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OneButtonDialog(getActivity(), OneButtonDialog.DIALOG_TYPE.INPUT_ONLY)
                        .setTitle(getString(R.string.enter_plan))
                        .setInputType(InputType.TYPE_CLASS_NUMBER)
                        .setEditTextHint(getString(R.string.budget))
                        .setCustomTextStyle(R.style.standard_margin)
                        .setCustomTitleStyle(R.style.dialog_title_style)
                        .build();
            }
        });
        return root;
    }

}
