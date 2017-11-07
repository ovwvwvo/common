package com.ovwvwvo.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络连接状态工具类
 */
public class NetworkUtil {

    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接(不能判断手机是否是电脑共享上网)
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = null;
        intent = new Intent(
            android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 判断网络连接类型 //但是不能判断出是使用的电脑共享上网
     */
    public static String getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        State mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            .getState();
        State wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            .getState();
        if (wifi.equals(State.CONNECTED)) {
            return "wifi";
        } else if (mobile.equals(State.CONNECTED)) {
            return "移动数据";
        } else {
            return "NO_CONNECTION"; // 包含电脑共享上网
        }
    }

    /**
     * 获取网络连接类型
     *
     * @return 如 WiFi
     */
    public static String getNetworkTypeName1(Context context) {
        if (context != null) {
            ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectMgr != null) {
                NetworkInfo info = connectMgr.getActiveNetworkInfo();
                if (info != null) {
                    switch (info.getType()) {
                        case ConnectivityManager.TYPE_WIFI:
                            return "WIFI";
                        case ConnectivityManager.TYPE_MOBILE:
                            return getNetworkTypeName2(info.getSubtype());
                    }
                }
            }
        }
        return getNetworkTypeName2(TelephonyManager.NETWORK_TYPE_UNKNOWN);
    }

    /**
     * 获取网络型号
     *
     * @return 如：CDMA
     */
    public static String getNetworkTypeName2(int type) {
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "CDMA - EvDo rev. 0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "CDMA - EvDo rev. A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return "CDMA - EvDo rev. B";
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "CDMA - 1xRTT";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return "CDMA - eHRPD";
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "iDEN";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "HSPA+";
            default:
                return "UNKNOWN";
        }
    }

    /**
     * 返回运营商+网络类型名称
     *
     * @return 如：移动4G
     */
    public String getNetworkTypeName3(Context context) {
        String type = getNetworkTypeName1(context);
        if (type != null) {
            if (type.equals("GPRS") || type.equals("EGDE"))
                type = "2G";
            else if (type.equals("CDMA"))
                type = "电信 2G";
            else if (type.equals("EVDO"))
                type = "电信 3G";
            else if (type.equals("LTE"))
                type = "电信 4G";
            else if (type.equals("HSDPA") || type.equals("UMTS"))
                type = "3G";
        }
        return type;
    }

    /**
     * 获取WiFi的IP地址
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     * <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
     */
    public static String getWIFiAddress(Context context) {
        @SuppressLint("WifiManagerPotentialLeak") WifiManager wifiManager = (WifiManager) context
            .getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        String ip = "";
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        ip = intToIp(ipAddress);
        return ip;
    }

    private static String intToIp(int i) {

        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
            + "." + (i >> 24 & 0xFF);
    }

    /**
     * 获取GPRS手机的IP
     */
    public static String getLocalIpAddress() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                    .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = inetAddress.getHostAddress().toString();
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("NewWorkUtil", ex.getMessage());
        }
        return ip;
    }

}
