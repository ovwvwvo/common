/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.content.res.Resources;

import com.ovwvwvo.common.AppWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {

    public static String formatDate(Date date, String format) {
        String result = null;
        Resources resources = AppWrapper.getInstance().getAppContext().getResources();
        Locale locale = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            locale = resources.getConfiguration().getLocales().get(0);
        } else
            locale = resources.getConfiguration().locale;

        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
            try {
                result = dateFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return result;
    }
}
