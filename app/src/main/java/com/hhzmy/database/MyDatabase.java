package com.hhzmy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by mis on 2016/10/20.
 */
public class MyDatabase extends SQLiteOpenHelper{
    public MyDatabase(Context context) {
        super(context, "my.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table shopping (_id integer primary key autoincrement,goods_name text,shop_price text,numShow text,goods_img text,is_activity_goods text,is_allow_credit text,url text,state text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void  updataTo2(List<String> ids){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("state", "2");
//LIST 转字符数组
        int size = ids.size();
        Log.e("MyDatabase","ids.size()"+ids.size());

        String[] arr = (String[])ids.toArray(new String[size]);
        for (int i = 0; i < arr.length; i++) {
            Log.e("MyDatabase","arr[]"+arr[i]);
            db.update("shopping", values, "_id=?", new String[]{arr[i]});
        }
        Log.e("MyDatabase",arr.length+"");
//        db.update("shopping", values, "_id=?", arr);
        //3. 关闭数据库
        db.close();
    }
    public void insertData(List<String> ids){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
//        db.insert("shopping",);
        db.close();
    }

    public void getData(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
    }
}
