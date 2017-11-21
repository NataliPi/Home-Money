package com.natali_pi.home_money;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Natali-Pi on 18.11.2017.
 */

public class BaseActivity extends AppCompatActivity {
protected void hideHighlight(){
    findViewById(R.id.highlight).setVisibility(View.GONE);
}

    public void setupToolbar(int navigationDrawableId, String title) {
        RelativeLayout toolbar = (RelativeLayout) findViewById(R.id.toolbar);

        ((TextView)toolbar.findViewById(R.id.titleText)).setText(title);
        ((ImageView)toolbar.findViewById(R.id.navigationButton)).setImageResource(navigationDrawableId);
        toolbar.setVisibility(View.VISIBLE);
    }

    public void setupOption(int drawableId) {
        ((ImageView)findViewById(R.id.optionButton)).setImageResource(drawableId);
    }

    protected void setOptionButtonListener (View.OnClickListener listener){
        ImageView optionButton = (ImageView) findViewById(R.id.optionButton);
        optionButton.setOnClickListener(listener);
    }
    public void setupLabel(String text){
        TextView label = (TextView) findViewById(R.id.label);
        label.setText(text);
        label.setVisibility(View.VISIBLE);
    }

    public void setupSideDrawer (){
        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer != null){
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            findViewById(R.id.navigationButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    protected void setBaseContentView(@LayoutRes int layoutId){
        setContentView(R.layout.main_holder_layout);
        LinearLayout mainHolder = (LinearLayout) findViewById(R.id.mainHolder);
        mainHolder.addView(getLayoutInflater().inflate(layoutId, mainHolder, false));
        ((DrawerLayout)findViewById(R.id.drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    protected void setupFont(TextView view){
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/YuGothB.ttc");
        view.setTypeface(custom_font);
    }
    protected void setNavigationButtonListener (View.OnClickListener listener){
        ImageView navigationButton = (ImageView) findViewById(R.id.navigationButton);
        navigationButton.setOnClickListener(listener);
    }
    protected void setHighlightedText(String text){
        TextView highlitedText = (TextView) findViewById(R.id.highlitedText);
        highlitedText.setText(text);
    }
}
