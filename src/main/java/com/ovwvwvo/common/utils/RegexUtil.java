/*
 *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 */
public class RegexUtil {

    private static final String EMAIL_PATTERN =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";

    public static boolean isEmail(String strEmail) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        return !isEmpty(strEmail) && emailPattern.matcher(strEmail).matches();
    }

    private static final String PASSWORD_PATTERN = "^[\\d_!@#$%*+^&a-zA-Z]{6,12}$";

    public static boolean isPassword(String strPassword) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        return !isEmpty(strPassword) && passwordPattern.matcher(strPassword).matches();
    }

    private static final String CHINESE_PHONE_PATTERN = "^1[3578]\\d{9}";

    public static boolean isChinaPhoneNumber(String phoneNum) {
        Pattern pattern = Pattern.compile(CHINESE_PHONE_PATTERN);
        return !isEmpty(phoneNum) && pattern.matcher(phoneNum).matches();
    }

    private static final String INTERNATIONAL_PHONE_PATTERN = "^\\d{1,4}-\\d{3,11}$";

    public static boolean isInternationalPhoneNumber(String phoneNum) {
        Pattern pattern = Pattern.compile(INTERNATIONAL_PHONE_PATTERN);
        return !isEmpty(phoneNum) && pattern.matcher(phoneNum).matches();
    }

    public static boolean isPhoneNumber(String phoneNum) {
        return isChinaPhoneNumber(phoneNum) || isInternationalPhoneNumber(phoneNum);
    }

    public static boolean isNumber(String numStr) {
        return !isEmpty(numStr) && numStr.matches("[0-9]+");
    }

    private static final String NIKENAME = "^[A-Za-z0-9\\u4E00-\\u9FA5_-]+$";// 用户名（只含有英文、数字、中文字符）

    public static boolean isNikeName(String nikeName) {
        if (TextUtils.isEmpty(nikeName)) {
            return false;
        }
        return nikeName.matches(NIKENAME);
    }

    /**
     * 过滤特殊字符
     */
    public static String StringFilter(String str) {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    private static boolean isEmpty(@Nullable CharSequence str) {
        return (str == null || str.length() == 0);
    }

}
