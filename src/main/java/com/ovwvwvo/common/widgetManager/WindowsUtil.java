/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.widgetManager;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * View工具类
 */
public class WindowsUtil {

    /**
     * 修改窗体的透明度  alpha :透明度 （0-1.0f）越大越透明
     */
    public static void changeAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 修改整个界面所有控件的字体
     * path 字体路径
     */
    public static void changeFonts(ViewGroup root, String path, Activity act) {
        Typeface tf = Typeface.createFromAsset(act.getAssets(), path);
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
                changeFonts((ViewGroup) v, path, act);
            }
        }
    }

    /**
     * 修改整个界面所有控件的字体大小
     */
    public static void changeTextSize(ViewGroup root, int size, Activity act) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTextSize(size);
            } else if (v instanceof ViewGroup) {
                changeTextSize((ViewGroup) v, size, act);
            }
        }
    }

    /**
     * 不改变控件位置，修改控件大小
     */
    public static void changeWH(View v, int W, int H) {
        LayoutParams params = (LayoutParams) v.getLayoutParams();
        params.width = W;
        params.height = H;
        v.setLayoutParams(params);
    }

    /**
     * 修改控件的高
     */
    public static void changeH(View v, int H) {
        LayoutParams params = (LayoutParams) v.getLayoutParams();
        params.height = H;
        v.setLayoutParams(params);
    }

}
