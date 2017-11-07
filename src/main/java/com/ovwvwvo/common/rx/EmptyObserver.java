/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.rx;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class EmptyObserver<T> implements Observer<T> {

    public static final String TAG = "EmptyObserver";

    public EmptyObserver() {
    }

    public void onCompleted() {
    }

    public void onError(Throwable e) {
        Log.e(TAG, "onError", e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public void onNext(T t) {
    }
}
