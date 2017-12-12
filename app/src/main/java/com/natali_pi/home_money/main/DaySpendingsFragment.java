package com.natali_pi.home_money.main;

import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Money;
import com.natali_pi.home_money.models.Spending;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natali-Pi on 10.12.2017.
 */

public class DaySpendingsFragment extends BaseFragment {
    int screenWidth = -1;
    int margin = -1;
    private List<Spending> spendings;

    @Override
    protected void resolveDaggerDependencies() {
    }

    @Override
    protected int contentView() {
        return R.layout.fragment_day_spendings;
    }

    public void setSpendings(List<Spending> spendings) {
        this.spendings = spendings;
    }
private Spending getLargest(List<Spending> spendings){
    Spending largest = null;
    for (Spending spending : spendings) {
        if(largest == null){
            largest = spending;
        } else if(largest.getSum().lessThen(spending.getSum())){
            largest = spending;
        }
    }
    return largest;
}
    private Spending getSmallest(List<Spending> spendings){
        Spending smallest = null;
        for (Spending spending : spendings) {
            if(smallest == null){
                smallest = spending;
            } else if(spending.getSum().lessThen(smallest.getSum())){
                smallest = spending;
            }
        }
        return smallest;
    }
    @Override
    protected View onCreateView(View root) {
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.dayLayout);
        LinearLayout line = new LinearLayout(getActivity());

        line.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(line);

        List<Spending> large = getSpendings(SIZE._2X2);
        List<Spending> medium = getSpendings(SIZE._2X1);
        List<Spending> small = getSpendings(SIZE._1X1);
        if(small == null){
            if(medium == null){
                small = large;
                large = null;
            } else {
                small = medium;
                medium = large;
                large = null;
            }
        } else{
            if (medium == null){
                medium = large;
                large = null;
            }
        }
boolean largeLineFinished = true;
        if(large != null){
            for (int i = 0; i < large.size(); i++) {

                line.addView(prepareSpendingView(SIZE._2X2));
                if(i % 2 != 0) {
                    line = new LinearLayout(getActivity());
                    line.setOrientation(LinearLayout.HORIZONTAL);
                    layout.addView(line);
                    largeLineFinished = true;
                } else {
                    largeLineFinished = false;
                }
            }
        }

int rectsInLine = 0;
        if(medium != null){
            LinearLayout lineHolder = new LinearLayout(getActivity());

            SIZE size = randomizeSize();
            if(!largeLineFinished) {
                lineHolder.setOrientation(LinearLayout.HORIZONTAL);
                line.addView(lineHolder);
                line = new LinearLayout(getActivity());
                line.setOrientation(size==SIZE._2X1 ? LinearLayout.VERTICAL :  LinearLayout.HORIZONTAL);
                lineHolder.addView(line);
            }
            for (int i = 0; i < medium.size(); i++) {

                if(i % 2 == 0) {
                    if(largeLineFinished){
                        rectsInLine = 0;
                        lineHolder = new LinearLayout(getActivity());
                        lineHolder.setOrientation(LinearLayout.HORIZONTAL);
                        layout.addView(lineHolder);
                        line = new LinearLayout(getActivity());
                        line.setOrientation(size==SIZE._2X1 ? LinearLayout.VERTICAL :  LinearLayout.HORIZONTAL);
                        lineHolder.addView(line);
                    }
                    line.addView(prepareSpendingView(size));
                    rectsInLine++;
                } else {
                    line.addView(prepareSpendingView(size));
                    rectsInLine++;
                    size = randomizeSize();
                    if (!largeLineFinished){
                        line = new LinearLayout(getActivity());
                        line.setOrientation(size==SIZE._2X1 ? LinearLayout.VERTICAL :  LinearLayout.HORIZONTAL);
                        lineHolder.addView(line);
                        largeLineFinished = true;
                    } else {
                        line = new LinearLayout(getActivity());
                        line.setOrientation(size==SIZE._2X1 ? LinearLayout.VERTICAL :  LinearLayout.HORIZONTAL);
                        lineHolder.addView(line);
                        largeLineFinished = false;
                    }

                }
            }
        }

        if (small != null){
            for (int i = 0; i < small.size(); i++) {
if (largeLineFinished){

    line = new LinearLayout(getActivity());
    layout.addView(line);
} else {
   // rectsInLine;
}
                line.addView(prepareSpendingView(SIZE._1X1));
            }
        }

        return root;
    }
private SIZE randomizeSize(){
    return Math.random()>=0.5d ? SIZE._2X1 : SIZE._1X2;
}


    private List<Spending> getSpendings(SIZE size){

        Spending largest = getLargest(this.spendings);

        Money lowThreshold = largest.getSum().divideBy(3.0f);
        Money highThreshold = Money.substract(largest.getSum(), lowThreshold);

        List<Spending> result = null;
        if (size == SIZE._2X2) {
            for (Spending spending : spendings) {
                if(highThreshold.lessThen(spending.getSum())) {
                    if(result == null){
                        result = new ArrayList<>();
                    }
                    result.add(spending);
                }
            }
        } else if (size == SIZE._2X1 || size == SIZE._1X2){
            for (Spending spending : spendings) {
                if(spending.getSum().lessThen(highThreshold)
                        && lowThreshold.lessThen(spending.getSum()) ) {
                    if(result == null){
                        result = new ArrayList<>();
                    }
                    result.add(spending);
                }
            }
        } else if(size == SIZE._1X1){
            for (Spending spending : spendings) {
                if(spending.getSum().lessThen(lowThreshold)) {
                    if(result == null){
                        result = new ArrayList<>();
                    }
                    result.add(spending);
                }
            }
        }
        return result;
    }
    private ImageView prepareSpendingView(SIZE size){
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.health);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        switch (size){
            case _2X2:
                imageView.setLayoutParams(getLayoutParams2x2());
                break;
            case _1X1:
                imageView.setLayoutParams(getLayoutParams1x1());
                break;
            case _1X2:
                imageView.setLayoutParams(getLayoutParams1x2());
                break;
            case _2X1:
                imageView.setLayoutParams(getLayoutParams2x1());
                break;
        }
        return imageView;
    }

    private int getScreenWidth() {
        if (screenWidth == -1) {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x - getMargin();
        }
        return screenWidth;

    }
private int getMargin(){
    if(margin == -1){
        margin = (int)getActivity().getResources().getDimension(R.dimen.standard_margin);
    }
    return margin;
}
    private LinearLayout.LayoutParams getLayoutParams1x1(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( get1cellSize(),  get1cellSize());
        lp.setMargins(getMargin(),getMargin(),0,0);
        return lp;
    }
    private LinearLayout.LayoutParams getLayoutParams1x2(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( get1cellSize(),  get2cellSize());
        lp.setMargins(getMargin(),getMargin(),0,0);
        return lp;
    }
    private LinearLayout.LayoutParams getLayoutParams2x1(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( get2cellSize(),  get1cellSize());
        lp.setMargins(getMargin(),getMargin(),0,0);
        return lp;
    }
    private LinearLayout.LayoutParams getLayoutParams2x2(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( get2cellSize(),  get2cellSize());
        lp.setMargins(getMargin(),getMargin(),0,0);
        return lp;
    }

    private int get2cellSize() {
        return (int) ( (((float)getScreenWidth()) /2.0f )-getMargin());
    }
    private int get1cellSize() {
        return (int) ( (((float)getScreenWidth()) /4.0f )-getMargin());
    }
    enum SIZE{
        _1X1,
        _2X2,
        _2X1,
        _1X2;

    }


    /*
    1
    20
    60
    400
    500
    ------------ 500
    502
    900
    1000
    ------------ 1000
    1500






    78
    79
    90
    ----- 500
    ----- 1000
    1500

    int trashold = max - min / 3
    int largeTrashold = max - trashold;
    int smallTrashold = min + trashold;
    1500-400 = 1100     /3 = 375







    */




}
