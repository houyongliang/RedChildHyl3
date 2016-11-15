package com.hhzmy.httputil;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mis on 2016/11/8.
 */

public class Utils {
    public static void putSP(Context context, String key, Boolean value){
        SharedPreferences sp= context.getSharedPreferences("isFirest",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static Boolean getSP(Context context, String key){
        SharedPreferences sp= context.getSharedPreferences("isFirest",Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
    }


}
