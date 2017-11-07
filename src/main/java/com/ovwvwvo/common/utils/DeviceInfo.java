/*
 * *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * 获取设备的信息
 */
public final class DeviceInfo {
    /**
     * 获取设备的基本信息，需要权限（READ_PHONE_STATE）
     */
    public static void getDeviceInfo(Context context) {
        TelephonyManager mTm = (TelephonyManager) context
            .getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = mTm.getDeviceId();
        String IMSI = mTm.getSubscriberId();
        String MTYPE = Build.MODEL; // 手机型号
        String SDK = Build.VERSION.SDK; // SDK版本
        String NUMBER = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
    }

    /**
     * 获取屏幕的高和宽
     */
    public static int[] getWidth_Height(Context context) {

        // 方法一
        // DisplayMetrics dm = new DisplayMetrics();
        // context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // int width = dm.widthPixels;
        // int height = dm.heightPixels;

        // 方法2
        WindowManager mWindowManager = (WindowManager) context
            .getSystemService(Context.WINDOW_SERVICE);
        int width = mWindowManager.getDefaultDisplay().getWidth();
        int height = mWindowManager.getDefaultDisplay().getHeight();
        return new int[]{width, height};
    }

}
