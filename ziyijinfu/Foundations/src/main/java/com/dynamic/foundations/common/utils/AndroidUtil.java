package com.dynamic.foundations.common.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2014-09-25
 */
public class AndroidUtil {
    private static final String TAG = AndroidUtil.class.getSimpleName();

    public static String getMacAddress(Context context) {
        //wifi mac地址
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        return mac;
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceID = "" + tm.getDeviceId();
        return deviceID;
    }

    public static String getCombineCode(Context context) {
        String s = getAndroidID(context) + getDeviceID(context) + getIMEI(context) + getMacAddress(context);

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(), 0, s.length());
// get md5 bytes
        byte p_md5Data[] = m.digest();
// create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
// if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID += "0";
// add number to string
            m_szUniqueID += Integer.toHexString(b);
        }   // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();

        return m_szUniqueID;
    }

    public static String printSystemInfo() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------  系统信息  " + time + "  --------------------");
        sb.append("\nBOARD        :" + android.os.Build.BOARD);
        sb.append("\nDEVICE       :" + android.os.Build.DEVICE);
        sb.append("\nMANUFACTURER :" + android.os.Build.MANUFACTURER);
        sb.append("\nPRODUCT      :" + android.os.Build.PRODUCT);
        sb.append("\nCODENAME     :" + android.os.Build.VERSION.CODENAME);
        sb.append("\nRELEASE      :" + android.os.Build.VERSION.RELEASE);
        sb.append("\nSDK          :" + android.os.Build.VERSION.SDK);
        return sb.toString();
    }

}
