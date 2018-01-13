package com.natali_pi.home_money.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.natali_pi.home_money.DraweredActivity;
import com.natali_pi.home_money.add_spending.SpendingActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.utils.DataBase;

public class MainActivity extends DraweredActivity {
MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
        setBaseContentView(R.layout.activity_main);
        setupToolbar(R.drawable.burger, "");
        setupOption(R.drawable.plus);
        setupLabel(getString(R.string.last_spendings));
        setupSideDrawer();

        setOptionButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpendingActivity.class);
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
        if(DataBase.getInstance().getFamily().getSpendings()!= null&&DataBase.getInstance().getFamily().getSpendings().size()>0) {
            DaySpendingsFragment daySpendingsFragment = new DaySpendingsFragment();
            daySpendingsFragment.setSpendings(DataBase.getInstance().getFamily().getSpendings());
            getFragmentManager().beginTransaction().add(R.id.list, daySpendingsFragment).commit();
        }
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


    @Override
    protected void onBitmapLoaded(DraweredActivity.TAG tag, Bitmap bitmap) {
        presenter.uploadPicture(tag, bitmap);
    }
}