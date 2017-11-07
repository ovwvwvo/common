/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回字符串的长度（中文一个字符长度为2）
     */
    public static int getLengthStr(String s) {
        int chCharacter = 0, enCharacter = 0, other = 0;

        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                enCharacter++;
            } else if (isChineseChar(tmp)) {
                chCharacter += 2;
            } else {
                other++;
            }
        }
        return chCharacter + enCharacter + other;
    }

    public static boolean isChineseChar(char ch) {
        // 获取此字符的UniCodeBlock
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
