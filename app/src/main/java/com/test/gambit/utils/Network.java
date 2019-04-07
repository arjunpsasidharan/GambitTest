package com.test.gambit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import java.lang.reflect.Method;


public class Network {

    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkAvailable = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm!=null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    isNetworkAvailable = true;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    isNetworkAvailable = true;
                }
            } else {
                // not connected to the internet
                isNetworkAvailable = false;

            }
        }
        return isNetworkAvailable;
    }

    public static boolean isNetworkEnabled(Context context) {
        boolean isNetworkAvailable = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if(cm!=null) {
                Class<?> cmClass = Class.forName(cm.getClass().getName());
                Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
                method.setAccessible(true); // Make the method callable
                // get the setting for "mobile data"
                isNetworkAvailable = (Boolean) method.invoke(cm);
            }

            if(!isNetworkAvailable) {
                WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if(wifi!=null)
                    isNetworkAvailable = wifi.isWifiEnabled();
            }

        } catch (Exception e) {
        }

        return isNetworkAvailable;
    }



}
