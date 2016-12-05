package com.hhzmy.httputil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import static vi.com.gdi.bgl.android.java.EnvDrawText.buffer;

/**
 * Created by mis on 2016/11/8.
 */

public class Utils {
    public static void putSP(Context context, String key, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences("isFirest", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getSP(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("isFirest", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static String stream2String(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0;
        byte[] buffer = new byte[2048];
        while ((length = in.read(buffer))!=-1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString();
    }

    public static String stream2String2(InputStream in) throws IOException {
        BufferedReader br=new BufferedReader( new InputStreamReader(in));
        StringBuffer sb=new StringBuffer();
       String len="";

        while ((len = br.readLine())!=null) {
            sb.append(len);
        }
        return sb.toString();
    }

}
