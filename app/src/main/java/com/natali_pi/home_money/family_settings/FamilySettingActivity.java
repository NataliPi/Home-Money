package com.natali_pi.home_money.family_settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.natali_pi.home_money.DraweredActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.utils.OneButtonDialog;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class FamilySettingActivity extends DraweredActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_family_setting);
        setHighlightedText(getString(R.string.family_setting));
        setupSideDrawer();
        final ImageView familyPhoto = (ImageView) findViewById(R.id.familyPhoto);
        final ImageView whitephoto = (ImageView) getDrawer().findViewById(R.id.whitephoto);
        whitephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(getPickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult result) {
                                familyPhoto.setImageBitmap(result.getBitmap());
                            }
                        }).show(FamilySettingActivity.this);
            }
        });

        TextView deleteFamily = (TextView) findViewById(R.id.deleteFamily);
        deleteFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OneButtonDialog(FamilySettingActivity.this, OneButtonDialog.DIALOG_TYPE.MESSAGE_ONLY)
                        .setMessage(getString(R.string.leave_family_dialog_message))
                        .setTitle(getString(R.string.leave_family_dialog_title, "Пятковские"))
                        .setCustomTitleStyle(R.style.dialog_title_style)
                        .build();
            }
        });
        TextView joinTheFamily = (TextView) findViewById(R.id.joinToFamily);
        joinTheFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilySettingActivity.this, DecoderActivity.class);
                startActivity(intent);
            }
        });
        TextView addToFamily = (TextView) findViewById(R.id.addToFamily);
        addToFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilySettingActivity.this, GenerateQRActivity.class);
                startActivity(intent);
            }
        });
    }

}