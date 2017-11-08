/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.content.Context;
import android.widget.Toast;

import com.ovwvwvo.common.AppWrapper;


/**
 * Toast工具类
 */
public class ToastMaster {

    private ToastMaster() {
    }

    public static void showToastMsg(int resId) {
        Context appContext = AppWrapper.getInstance().getAppContext();
        Toast.makeText(appContext, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToastMsg(String msg) {
        Context appContext = AppWrapper.getInstance().getAppContext();
        Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showErrorMsg(String msg, Throwable error) {
        Context appContext = AppWrapper.getInstance().getAppContext();
        StringBuilder builder = new StringBuilder(msg);
        if (error != null && error.getMessage() != null) {
            builder.append("\n");
            builder.append(error.getMessage());
        }
        Toast.makeText(appContext, builder.toString(), Toast.LENGTH_SHORT).show();
    }
}