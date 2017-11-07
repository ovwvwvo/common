package com.ovwvwvo.common.widget.manager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.AnyRes;

public class ShortCutUtil {

    public static void createShortCut(Context context, String name, Intent intent, @AnyRes int resourceId) {

        if (isInstallShortcut(context, name))
            return;
//        Intent intent = new Intent(Intent.ACTION_MAIN);
        // 加入action和category之后，程序卸载的时候才会主动将该快捷方式也卸载
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.setClass(context, ShortCutUtil.class);

        Intent installer = new Intent();
        installer.putExtra("duplicate", false); // 是否允许重复创建
        installer.putExtra("android.intent.extra.shortcut.INTENT", intent); // 跳转界面
        installer.putExtra("android.intent.extra.shortcut.NAME", name); // 名字
        installer.putExtra("android.intent.extra.shortcut.ICON_RESOURCE",
            Intent.ShortcutIconResource.fromContext(context,
                resourceId));// 图标
        installer.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(installer);
    }


    private static boolean isInstallShortcut(Context context, String name) {
        boolean isInstallShortcut = false;
        final ContentResolver cr = context.getContentResolver();
        String AUTHORITY = "com.android.launcher.settings";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/favorites?notify=true");

        Cursor c = cr.query(CONTENT_URI,
            new String[]{"title", "iconResource"}, "title=?",
            new String[]{name}, null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }

        if (c != null) {
            c.close();
        }

        if (isInstallShortcut) {
            return true;
        }

        AUTHORITY = "com.android.launcher2.settings";
        CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/favorites?notify=true");
        c = cr.query(CONTENT_URI, new String[]{"title", "iconResource"},
            "title=?", new String[]{name}, null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }

        if (c != null) {
            c.close();
        }

        AUTHORITY = "com.baidu.launcher";
        CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/favorites?notify=true");
        c = cr.query(CONTENT_URI, new String[]{"title", "iconResource"},
            "title=?", new String[]{name}, null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }

        if (c != null) {
            c.close();
        }

        return isInstallShortcut;
    }
}
