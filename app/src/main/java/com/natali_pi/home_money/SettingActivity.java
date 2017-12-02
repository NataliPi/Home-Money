package com.natali_pi.home_money;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_setting);
        setupToolbar(R.drawable.arrow, "");
        setupOption(R.drawable.ok);
        setHighlightedText(getString(R.string.settings));


    }
}
