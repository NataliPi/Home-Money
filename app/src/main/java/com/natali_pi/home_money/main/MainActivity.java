package com.natali_pi.home_money.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.natali_pi.home_money.DraweredActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.add_spending.SpendingActivity;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.utils.DataBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DraweredActivity {
    MainPresenter presenter;
    List<DaySpendingsFragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
        setBaseContentView(R.layout.activity_main);
        setupToolbar(R.drawable.burger, "");
        setupOption(R.drawable.plus);
        setupLabel(getString(R.string.last_spendings));
        setupSideDrawer();

        setOptionButtonListener((v)-> {
                Intent intent = new Intent(MainActivity.this, SpendingActivity.class);
                startActivity(intent);
        });
        hideHighlight();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setupScroller();
    }

    private void setupScroller() {
        ((ViewGroup)findViewById(R.id.list)).removeAllViews();
        fragments.removeAll(fragments);
        final TextView dateText = (TextView) findViewById(R.id.dateText);
        TextView noSpendingsMessage = (TextView) findViewById(R.id.noSpendingsMessage);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        if (DataBase.getInstance().getFamily().getSpendings() != null &&
                DataBase.getInstance().getFamily().getSpendings().size() > 0) {
            dateText.setVisibility(View.VISIBLE);
            noSpendingsMessage.setVisibility(View.GONE);
            ArrayList<ArrayList<Spending>> spendingsByMMonth = DataBase.getInstance().getFamily().getSpendingsByMMonth();
            for (int i = 0; i < spendingsByMMonth.size(); i++) {

                DaySpendingsFragment daySpendingsFragment = new DaySpendingsFragment();
                daySpendingsFragment.setSpendings(spendingsByMMonth.get(i));
                getFragmentManager().beginTransaction().add(R.id.list, daySpendingsFragment).commit();
                fragments.add(daySpendingsFragment);
            }

        }
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int pos = getPosition(dateText);
                for (int i = 0; i < fragments.size(); i++) {
                    if (pos >= fragments.get(i).getDatePosition() - dateText.getHeight() / 2) {
                        dateText.setText(fragments.get(i).getMonth());
                    } else {
                        break;
                    }
                }
            }
        });
    }

    private int getPosition(TextView textView) {
        int[] pos = new int[2];
        textView.getLocationOnScreen(pos);
        return pos[1];
    }

    @Override
    protected void onBitmapLoaded(DraweredActivity.TAG tag, Bitmap bitmap) {
        presenter.uploadPicture(tag, bitmap);
    }
}