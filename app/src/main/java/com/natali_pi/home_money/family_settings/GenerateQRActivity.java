package com.natali_pi.home_money.family_settings;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.utils.DataBase;


import net.glxn.qrgen.android.QRCode;

import java.util.UUID;

import it.auron.library.mecard.MeCard;

/**
 * Created by Konstantyn Zakharchenko on 29.11.2017.
 */

public class GenerateQRActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        ImageView qrCode = (ImageView) findViewById(R.id.qrCode);

        qrCode.setImageBitmap(QRCode.from(DataBase.getInstance().getFamily().getName()+"::"+DataBase.getInstance().getFamily().getId()+"::"+UUID.randomUUID().toString()).bitmap());
    }

}