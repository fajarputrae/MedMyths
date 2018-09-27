package com.coinbkt.medmyth.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SPMedmyth {
    public static final String IS_MUTE = "IS_MUTE";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setIsMute(Context context, Boolean isRegister) {
        getSharedPreference(context).edit().putBoolean(IS_MUTE, isRegister).commit();
    }

    public static Boolean getIsMute(Context context) {
        return getSharedPreference(context).getBoolean(IS_MUTE, false);
    }


    public static void saveString(Context activity, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        if (editor.commit()) {
            Log.d("SPMANAGER", "commited " + value);
        } else {
            Log.d("SPMANAGER", "not commited");
        }
    }

    public static void saveInt(Context activity, String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveBoolean(Context activity, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getString(Context activity, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String user_email = sharedPreferences.getString(key, null);
        return user_email;
    }

    public static int getInt(Context activity, String key) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        int latihan = sharedPreferences.getInt(key, 0);
        return latihan;
    }

    public static boolean getBoolean(Context activity, String key) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity);
        boolean latihan = sharedPreferences.getBoolean(key, false);
        return latihan;
    }
}
