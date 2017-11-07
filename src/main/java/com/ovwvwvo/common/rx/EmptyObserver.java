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

    public EmptyObserver() {
    }

    @Override
    public void onError(Throwable e) {
        Log.e("EmptyObserver", "onError", e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
    }
}
