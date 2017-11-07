/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.content.Context;
import android.widget.Toast;

import com.ovwvwvo.common.AppWrapper;
import com.ovwvwvo.common.rx.EmptyObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;


/**
 * Toast工具类
 */
public class ToastMaster {

    public ToastMaster() {
    }

    public static void showToastMsg(final int resId) {
        final Context appContext = AppWrapper.getInstance().getAppContext();
        Observable.empty().observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
            @Override
            public void run() {
                Toast.makeText(appContext, resId, Toast.LENGTH_SHORT).show();
            }
        }).subscribe(new EmptyObserver<>());
    }

    public static void showToastMsg(final String msg) {
        final Context appContext = AppWrapper.getInstance().getAppContext();
        Observable.empty().observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
            public void run() {
                Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show();
            }
        }).subscribe(new EmptyObserver<>());
    }

    public static void showErrorMsg(final String msg, final Throwable error) {
        final Context appContext = AppWrapper.getInstance().getAppContext();
        Observable.empty().observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
            public void run() {
                StringBuilder builder = new StringBuilder(msg);
                if (error != null && error.getMessage() != null) {
                    builder.append("\n");
                    builder.append(error.getMessage());
                }

                Toast.makeText(appContext, builder.toString(), Toast.LENGTH_SHORT).show();
            }
        }).subscribe(new EmptyObserver<>());
    }
}