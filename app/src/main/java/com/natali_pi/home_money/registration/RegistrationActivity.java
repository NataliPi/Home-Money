package com.natali_pi.home_money.registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;

public class RegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_registration);
        setupToolbar(R.drawable.arrow, "");
        setHighlightedText(getString(R.string.registration));
        setNavigationButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
