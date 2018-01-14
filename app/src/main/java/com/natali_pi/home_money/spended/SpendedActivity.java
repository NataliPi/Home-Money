package com.natali_pi.home_money.spended;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Spending;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class SpendedActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spended);
        Spending spending = (Spending) getIntent().getSerializableExtra(DATA);
        TextView spendedName = (TextView) findViewById(R.id.spendedName);
        spendedName.setText(spending.getName());

        TextView categoryName = (TextView) findViewById(R.id.categoryName);
        categoryName.setText(spending.getCategory());

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(spending.getDate());
        TextView sumText = (TextView) findViewById(R.id.sumText);
        sumText.setText(spending.getSum().toString() + " " + spending.getSum().getCurrency());
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.photo);
        if(spending.getPhoto() != null){
            Picasso.with(this).load(spending.getPhoto())
                    .transform(new CropCircleTransformation())
                    .into(imageView);
        }
    }
}
