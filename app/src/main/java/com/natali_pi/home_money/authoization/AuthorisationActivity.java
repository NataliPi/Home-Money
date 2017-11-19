package com.natali_pi.home_money.authoization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.login.LoginActivity;
import com.natali_pi.home_money.registration.RegistrationActivity;

public class AuthorisationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation);
        Button login = ( Button) findViewById(R.id.login);
        Button registration = ( Button) findViewById(R.id.registration);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthorisationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthorisationActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
