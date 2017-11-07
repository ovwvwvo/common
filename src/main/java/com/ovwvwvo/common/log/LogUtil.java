/*
 * *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.log;

import android.util.Log;

public class LogUtil {

    private static boolean enable = true;

    public static void setEnable(boolean enable) {
        LogUtil.enable = enable;
    }

    public static boolean isEnable() {
        return enable;
    }

    public static void v(String tag, Object obj) {
        if (enable) {
            String content = obj == null ? "null" : obj.toString();
            Log.v(tag, content);
        }

    }

    public static void i(String tag, Object obj) {
        if (enable) {
            String content = obj == null ? "null" : obj.toString();
            Log.i(tag, content);
        }

    }

    public static void d(String tag, Object obj) {
        if (enable) {
            String content = obj == null ? "null" : obj.toString();
            Log.d(tag, content);
        }

    }

    public static void w(String tag, Object obj) {
        if (enable) {
            String content = obj == null ? "null" : obj.toString();
            Log.w(tag, content);
        }

    }

    public static void e(String tag, Object obj, Throwable e) {
        if (enable) {
            String content = obj == null ? "null" : obj.toString();
            Log.e(tag, content, e);
        }

    }
}
