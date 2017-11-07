/*
 * *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.text.format.Formatter;

public final class MemeryUtil {

    /**
     *  系统的可用内存
     */
    public static String getNoUsed(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(context, mi.availMem);
    }

    /**
     *  系统的总内存
     */
    @SuppressLint("NewApi")
    public static String getToatl(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(context, mi.totalMem);
    }

    /**
     * 应用程序最大可用内存（不含）、空闲内存、虚拟机试图使用的最大内存量(应用程序从虚拟机开辟的内存)
     */
    public static long[] getAppMemery() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        return new long[]{maxMemory, freeMemory, totalMemory};
    }
}
