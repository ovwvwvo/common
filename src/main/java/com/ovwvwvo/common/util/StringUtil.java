/**
 * Copyright © 2016 by Teambition
 */

package com.ovwvwvo.common.util;

import android.support.annotation.Nullable;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods regarding java.lang.String
 */
public class StringUtil {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return (str == null || str.length() == 0);
    }

    public static boolean isNotEmpty(@Nullable CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return isEmpty(str) || isEmpty(str.trim());
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean equalWithNull(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        } else if (str1 != null) {
            return str1.equals(str2);
        }
        return false;
    }

    private static final String EMAIL_PATTERN =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";

    public static boolean isEmail(String strEmail) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        return isNotEmpty(strEmail) && emailPattern.matcher(strEmail).matches();
    }

    private static final String PASSWORD_PATTERN = "^[\\d_!@#$%*+^&a-zA-Z]{6,12}$";

    public static boolean isPassword(String strPassword) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        return isNotEmpty(strPassword) && passwordPattern.matcher(strPassword).matches();
    }

    private static final String CHINESE_PHONE_PATTERN = "^1[3578]\\d{9}";

    public static boolean isChinaPhoneNumber(String phoneNum) {
        Pattern pattern = Pattern.compile(CHINESE_PHONE_PATTERN);
        return isNotEmpty(phoneNum) && pattern.matcher(phoneNum).matches();
    }

    private static final String INTERNATIONAL_PHONE_PATTERN = "^\\d{1,4}-\\d{3,11}$";

    public static boolean isInternationalPhoneNumber(String phoneNum) {
        Pattern pattern = Pattern.compile(INTERNATIONAL_PHONE_PATTERN);
        return isNotEmpty(phoneNum) && pattern.matcher(phoneNum).matches();
    }

    public static boolean isPhoneNumber(String phoneNum) {
        return isChinaPhoneNumber(phoneNum) || isInternationalPhoneNumber(phoneNum);
    }

    public static boolean isNumber(String numStr) {
        return isNotEmpty(numStr) && numStr.matches("[0-9]+");
    }

    public static String convertToString(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            strBuilder.append(strArr[i]);
        }
        return strBuilder.toString();
    }

    public static boolean containsIgnoreCase(CharSequence base, CharSequence sub) {
        if (isEmpty(base) || isEmpty(sub)) {
            return false;
        }

        String baseLower = base.toString().toLowerCase(Locale.getDefault());
        String subLower = sub.toString().toLowerCase(Locale.getDefault());
        return baseLower.contains(subLower);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (isNotBlank(str)) {
            Pattern p = Pattern.compile("\\s");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
