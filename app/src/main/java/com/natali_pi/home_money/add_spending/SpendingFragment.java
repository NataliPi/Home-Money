package com.natali_pi.home_money.add_spending;

import android.text.style.TtsSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class SpendingFragment extends BaseFragment{
    @Override
    protected void resolveDaggerDependencies() {

    }

    @Override
    protected int contentView() {
        return R.layout.fragment_add_spending;
    }

    @Override
    protected View onCreateView(View root) {
        LinearLayout addComponent = (LinearLayout) root.findViewById(R.id.addComponent);
        final ListView componentsHolder = (ListView) root.findViewById(R.id.componentsHolder);
        addComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return root;
    }
}
