/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.widget.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * TextView 处理工具类
 */
public class TextViewUtil {

    public static void setTopImageResource(Context context, TextView tv, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, drawable, null, null);
    }

    public static void setLeftImageResource(Context context, TextView tv, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setRightImageResource(Context context, TextView tv, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setBottomImageResource(Context context, TextView tv, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, null, drawable);
    }

}
