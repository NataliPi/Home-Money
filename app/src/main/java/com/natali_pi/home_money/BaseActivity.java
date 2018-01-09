package com.natali_pi.home_money;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.natali_pi.home_money.utils.OneButtonDialog;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.enums.EPickType;

/**
 * Created by Natali-Pi on 18.11.2017.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String DATA = "DATA";
    private DrawerLayout drawer;
    PickSetup pickSetup = null;
    public  PickSetup getPickSetup(){
        if (pickSetup == null){
            pickSetup = new PickSetup()
                    .setTitle(getString(R.string.download_image))
                    .setTitleColor(getResources().getColor(R.color.darkViolet))
                    .setBackgroundColor(getResources().getColor(R.color.white))
                    //.setProgressText("progresstext")
                    .setCancelText(getString(R.string.cancel))
                    .setCancelTextColor(getResources().getColor(R.color.darkViolet))
                    //.setButtonTextColor(yourColor)
                    //.setDimAmount(yourFloat)
                    //.setFlip(true)
                    //.setMaxSize(500)
                    .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                    //.setCameraButtonText(yourText)
                    //.setGalleryButtonText(yourText)
                    .setIconGravity(Gravity.LEFT)
                    //.setButtonOrientation(LinearLayoutCompat.VERTICAL)
                    .setSystemDialog(false)
                    //.setGalleryIcon(yourIcon)
                    //.setCameraIcon(yourIcon)
                        ;
        }
        return pickSetup;
    }
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

        if (getDrawer() != null){
            getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            findViewById(R.id.navigationButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDrawer().openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    protected void setBaseContentView(@LayoutRes int layoutId){
        setContentView(R.layout.main_holder_layout);
        LinearLayout mainHolder = (LinearLayout) findViewById(R.id.mainHolder);
        mainHolder.addView(getLayoutInflater().inflate(layoutId, mainHolder, false));
        getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    protected DrawerLayout getDrawer(){
        if (drawer == null) {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        }
            return drawer;
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
    protected BackOnPress getBackAction(){
        return new BackOnPress();
    }

    public void showMessage(String text){
        new OneButtonDialog(this, OneButtonDialog.DIALOG_TYPE.MESSAGE_ONLY)
                .setMessage(text)
                //.setCustomTextStyle(R.style.standard_margin)
                .setCustomTextStyle(R.style.dialog_title_style)
                .build();
    }
    public void onError() {
        onFinishLoading();
    }

    public void onFinishLoading() {
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
    }

    public void onStartLoading() {
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
    }

    protected class BackOnPress implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
