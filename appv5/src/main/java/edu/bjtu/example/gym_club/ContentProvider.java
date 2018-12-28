package edu.bjtu.example.gym_club;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class ContentProvider {
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    public ContentProvider(Context context){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        db = mySQLiteOpenHelper.getWritableDatabase();
    }

    public List getInfo(String tablename){
        return MySQLiteOpenHelper.getInfo(tablename,db);
    }

    public void setInfo(String tablename, List data){
        MySQLiteOpenHelper.setInfo(tablename,data,db);
    }
}
