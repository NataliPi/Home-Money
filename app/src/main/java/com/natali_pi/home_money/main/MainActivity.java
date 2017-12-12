package com.natali_pi.home_money.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.natali_pi.home_money.DraweredActivity;
import com.natali_pi.home_money.budget.BudgetActivity;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.planned_spending.PlannedSpendingActivity;
import com.natali_pi.home_money.settings.SettingActivity;
import com.natali_pi.home_money.add_spending.AddSpendingActivity;
import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.family_settings.FamilySettingActivity;
import com.natali_pi.home_money.search.Search;
import com.natali_pi.home_money.statistic.StatisticActivity;
import com.natali_pi.home_money.utils.DataBase;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DraweredActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_main);
        setupToolbar(R.drawable.burger, "");
        setupOption(R.drawable.plus);
        setupLabel(getString(R.string.last_spendings));
        setupSideDrawer();
        setOptionButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSpendingActivity.class);
                startActivity(intent);
            }
        });
        hideHighlight();
        setupScroller();

    }







    private void setupScroller() {
        final TextView dateLabel = (TextView)findViewById(R.id.dateLabel);
        final TextView dateLabel3 = (TextView)findViewById(R.id.dateText3);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        DaySpendingsFragment daySpendingsFragment = new DaySpendingsFragment();
        daySpendingsFragment.setSpendings(DataBase.getInstance().getFamily().getSpended());
        getFragmentManager().beginTransaction().add(R.id.list, daySpendingsFragment).commit();

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
               /* int[] pos3 = new int[2];
                dateLabel3.getLocationOnScreen(pos3);
                int[] pos = new int[2];
                dateLabel.getLocationOnScreen(pos);
            if(pos[1] >= pos3[1]){
                dateLabel.setText(dateLabel3.getText());
            } else if(pos[1] < pos3[1]){
                dateLabel.setText("ОктябЫрь");
            }*/
            }
        });
    }



}