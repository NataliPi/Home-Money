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

import com.natali_pi.home_money.SettingActivity;
import com.natali_pi.home_money.add_spending.AddSpendingActivity;
import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;

public class MainActivity extends BaseActivity {

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

    @Override
    public void setupSideDrawer() {
        super.setupSideDrawer();
    ImageView imageView = (ImageView) getDrawer().findViewById(R.id.settings);
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
    });

        final TextView lastSpendings = (TextView) getDrawer().findViewById(R.id.last_spendings);
        lastSpendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSpendings.setBackgroundResource(R.color.yellow);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearMenu();
    }

    private void clearMenu(){
        final TextView lastSpendings = (TextView) getDrawer().findViewById(R.id.last_spendings);
        lastSpendings.setBackgroundResource(R.color.white);
    }

    private void setupScroller() {
        final TextView dateLabel = (TextView)findViewById(R.id.dateLabel);
        final TextView dateLabel3 = (TextView)findViewById(R.id.dateText3);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int[] pos3 = new int[2];
                dateLabel3.getLocationOnScreen(pos3);
                int[] pos = new int[2];
                dateLabel.getLocationOnScreen(pos);
            if(pos[1] >= pos3[1]){
                dateLabel.setText(dateLabel3.getText());
            } else if(pos[1] < pos3[1]){
                dateLabel.setText("ОктябЫрь");
            }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}