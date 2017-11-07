/*
 * *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class AppUtil {

    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 获取手机安装的软件名称和包名
     * return map(应用名称, 包名)
     */

    public static HashMap<String, String> getNameAndPackageName(Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
        List<PackageInfo> packages = context.getPackageManager()
            .getInstalledPackages(0);
        for (PackageInfo i : packages) {
            if ((i.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                map.put(i.applicationInfo
                        .loadLabel(context.getPackageManager()).toString(),
                    i.packageName);
            }
        }
        return map;
    }

    public static boolean openApp(Context context, String packageName) {
        PackageManager mPM = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = mPM.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        if (pi == null)
            return false;

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pi.packageName);

        List<ResolveInfo> apps = mPM.queryIntentActivities(intent, 0);

        ResolveInfo ri;
        try {
            ri = apps.iterator().next();
        } catch (Exception e) {
            return false;
        }

        if (ri == null)
            return false;

        String tmpPackageName = ri.activityInfo.packageName;
        String className = ri.activityInfo.name;

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        ComponentName cn = new ComponentName(tmpPackageName, className);

        i.setComponent(cn);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(i);
        return true;
    }

    public static void installApp(Context context, String filePath) {
        File file = new File(filePath);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }

    public static void UninstallApk(Context context, String strPackageName) {
        Uri packageURI = Uri.parse("package:" + strPackageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

    public static boolean checkAppExist(Context context, String packname) {
        if (packname.equals(""))
            return false;
        try {
            context.getPackageManager().getPackageInfo(packname,
                PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

}
