package com.justnow.androidsummarize.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 本地生成：IMEI、MAC、Android_ID、Serial，然后加随机数，然后 Hash (MD5) 取定长
 * 本地存储：可以通过多处文件存储的方式，接近永久保留此唯一标识的效果
 */
public class AndroidDeviceId {

    /**
     * IMEI 国际移动设备身份码 目前GSM/WCDMA/LTE手机终端需要使用IMEI号码，
     * 在单卡工程中一个手机号对应一个IMEI号，双卡手机则会对应两个IMEI号，一张是手机卡对应一个。
     *
     * 缺点：用户禁用掉相关权限就获取不到
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String result = "imei";
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                result = manager.getImei();
            } else {
                result = manager.getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Mac 指的就是我们设备网卡的唯一设别码，该码全球唯一
     * 一般称为物理地址，硬件地址用来定义设备的位置
     *
     * 缺点：手机必须具有上网功能
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        String macAddress = "mac";
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

    /**
     * 在设备首次运行的时候，系统会随机生成一64位的数字，并把这个数值以16进制保存下来，
     * 这个16进制的数字就是ANDROID_ID，但是如果手机恢复出厂设置这个值会发生改变。
     *
     * 缺点：手机恢复出厂设置以后该值会发生变化；国内Android定制的大环境下，有些设备是不会返回ANDROID_ID
     * @return
     */
    public static String getAndroidId() {
        return Settings.System.ANDROID_ID;
    }

    /**
     * 设备序列号，不需要申请权限
     *
     * 缺点：有些手机上会出现垃圾数据
     *
     * @return
     */
    public static String getSerial() {
        return Build.SERIAL;
    }

    public static String getMD5(String inputStr, boolean isUpperCase) {
        StringBuilder result = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputByte = inputStr.getBytes();
            byte[] buff = md.digest(inputByte);
            String temp;
            for (byte b : buff) {
                temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
