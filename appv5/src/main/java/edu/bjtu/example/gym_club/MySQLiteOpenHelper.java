package edu.bjtu.example.gym_club;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziming
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 2;
    //定义数据库名称
    public static final String SQL_NAME = "trainerInfo.db";
    //定义数据表名称,不存在则创建
    public String trainerInfo = "create table if not exists trainerInfo(id Integer primary key autoincrement,phone String,name String,sex String,address String,email String)";
    public String trainerArticleInfo = "create table if not exists trainerArticleInfo(id Integer primary key autoincrement,content String,picuri String,filename String)";

    //这里为了方便测试，就使用每次新建数据表的语句啦
    //public String personInfo = "create table person_info(_id integer primary key autoincrement,name char,sex char,address char,profession char)";
    public MySQLiteOpenHelper(Context context) {
        super(context, SQL_NAME, null, VERSION);
    }

    //创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(trainerInfo);
        db.execSQL(trainerArticleInfo);
    }

    // 更新数据库方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static List getInfo(String tablename, SQLiteDatabase db) {
        Cursor cursor = db.query(tablename, null, null, null, null, null, null);

        if (tablename.equals("trainerInfo")){
            List<trainersLocalItem> trainersLocalItems = new ArrayList<>();
            //判断游标是否为空
            if (cursor.moveToFirst()) {
                //遍历游标
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.move(i);
                    //获得ID
                    int id = cursor.getInt(0);

                    String phone = cursor.getString(1);

                    String name = cursor.getString(2);

                    String sex = cursor.getString(3);

                    String address = cursor.getString(4);

                    String email = cursor.getString(5);

                    trainersLocalItems.add(new trainersLocalItem(id,phone,email));
                }
            }
            return trainersLocalItems;
        }

        else if (tablename.equals("trainerArticleInfo")){
            List<newsLocalItem> newsLocalItems = new ArrayList<>();
            //判断游标是否为空
            if (cursor.moveToFirst()) {
                //遍历游标
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.move(i);
                    //获得ID
                    int id = cursor.getInt(0);

                    String content = cursor.getString(1);

                    String picuri = cursor.getString(2);

                    String filename = cursor.getString(3);

                    newsLocalItems.add(new newsLocalItem(id,content,picuri,filename));
                }
            }
            return newsLocalItems;
        }
        return null;
    }

    public static void setInfo(String tablename, List data, SQLiteDatabase db){
        //实例化常量值
        ContentValues cValue = new ContentValues();

        if (tablename.equals("trainerInfo")){
            for(trainersLocalItem item : (List<trainersLocalItem>)data){
                cValue.put("id",item.getId());
                cValue.put("name",item.getName());
                cValue.put("phone",item.getTel());
                cValue.put("email",item.getEmail());
            }
            db.insert(tablename,null,cValue);
        }else if(tablename.equals("trainerArticleInfo")) {
            for (newsLocalItem item : (List<newsLocalItem>) data) {
                cValue.put("id", item.getId());
                cValue.put("contents", item.getContents());
                cValue.put("fileName", item.getFileName());
                cValue.put("pictureUri", item.getPictureUri());
            }
            db.insert(tablename, null, cValue);
        }
    }
}

