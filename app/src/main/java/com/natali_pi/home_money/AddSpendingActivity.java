package com.natali_pi.home_money;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AddSpendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spending);
        LinearLayout addComponent = (LinearLayout) findViewById(R.id.addComponent);
        final LinearLayout componentsHolder = (LinearLayout) findViewById(R.id.componentsHolder);
        addComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText component = new EditText(AddSpendingActivity.this);
                componentsHolder.addView(component);

            }
        });
    }
   }
