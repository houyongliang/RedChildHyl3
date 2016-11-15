package com.hyl.mis.hyl.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mis on 2016/10/7.
 */
public class Utils {
    public static void putFirstSP(Context context, String key, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences("FirstEnter", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }


    public static Boolean getFirstSP(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("FirstEnter", Context.MODE_APPEND);
        return sp.getBoolean(key, false);
    }


    public static void putBufferJsonSP(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("BufferJson", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();

    }


    public static String getBufferJsonSP(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("BufferJson", Context.MODE_APPEND);
        return sp.getString(key, "");
    }


}
