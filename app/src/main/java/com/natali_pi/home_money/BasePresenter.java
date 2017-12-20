package com.natali_pi.home_money;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Natali-Pi on 13.12.2017.
 */

public abstract class BasePresenter {

    protected List<Disposable> disposables = new ArrayList<>();


    protected <Type> Observer<Type> getObserver(boolean isShowLoading, OnNext<Type> action) {
        return new Observer<Type>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
                if (isShowLoading) {
                    getView().onStartLoading();
                }
            }

            @Override
            public void onNext(Type t) {
                action.onNext(t);
                if (isShowLoading) {
                    getView().onFinishLoading();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isShowLoading) {
                    onCallError();
                } else {
                    Log.e("Presenter", e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                Log.v("Presenter", "completed");
            }
        };
    }

    private void onCallError() {
    getView().onError();
    }

     protected abstract BaseActivity getView();
    public interface OnNext<Type> {
        void onNext(Type data);
    }
}
