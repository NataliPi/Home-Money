package com.natali_pi.home_money.spended;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.add_spending.ComponentsAdapter;
import com.natali_pi.home_money.add_spending.SpendingActivity;
import com.natali_pi.home_money.main.MainActivity;
import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.utils.DataBase;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class SpendedActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_spended);
        setupToolbar(R.drawable.burger, "");
        setupOption(R.drawable.pen);
        setupLabel(null);
        setHighlightedText(null);
        setupSideDrawer();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    private void showData(){
        String spendingId = getIntent().getStringExtra(DATA);
        Spending spending =DataBase.getInstance().getFamily().getSpendingById(spendingId);
        //(Spending) getIntent().getSerializableExtra(DATA);
        setOptionButtonListener((v)-> {
            Intent intent = new Intent(SpendedActivity.this, SpendingActivity.class);
            intent.putExtra(DATA, spending);
            startActivity(intent);
        });
        TextView spendedName = (TextView) findViewById(R.id.spendedName);
        spendedName.setText(spending.getName());

        TextView categoryName = (TextView) findViewById(R.id.categoryName);
        Category category = DataBase.getInstance().getCategoryById(spending.getCategory());
        if(category.isStandard()){
            categoryName.setText(getStringResourceByName(category.getName()));
        }else {
            categoryName.setText(spending.getCategory());
        }
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
        ListView componentsHolder = (ListView) findViewById(R.id.componentsHolder);
        ComponentsAdapter componentsAdapter = new ComponentsAdapter(SpendedActivity.this, spending.getComponents(), false);
        componentsHolder.setAdapter(componentsAdapter);
    }
}
