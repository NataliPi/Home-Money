package com.natali_pi.home_money.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.main.MainActivity;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_setting);
        setupToolbar(R.drawable.arrow, "");
        setupOption(R.drawable.ok);
        setHighlightedText(getString(R.string.settings));

        final ImageView spendPhoto = (ImageView) findViewById(R.id.spend_photo);
        spendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(getPickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult result) {
                                spendPhoto.setImageBitmap(result.getBitmap());
                            }
                        }).show(SettingActivity.this);
            }
        });

    }
}
