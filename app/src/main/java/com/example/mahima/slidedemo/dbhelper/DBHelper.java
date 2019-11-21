package com.example.mahima.slidedemo.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "newsdb.db";
    public static final String CONTACTS_TABLE_NAME = "news_counter";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "cat";
    public static final String CONTACTS_COLUMN_clike = "c_like";
    public static final String CONTACTS_COLUMN_cvisit = "c_visit";
    private HashMap hp;
    Context context;

    private String cats[];

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        this.context = context;
        cats = new String[]{"ent","sport","crime","social","education","politics","business"};
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table news_counter " +
                        "(id integer primary key, cat text,c_like text,c_visit text)"
        );
        for(int i=0;i<cats.length;i++)
        {
            String q = "insert into news_counter values ('"+i+"','"+cats[i]+"','0','0')";
            db.execSQL(q);
        }

        Toast.makeText(context, "table created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS news_counter");
        onCreate(db);
    }
    public int deleteCat(String cat)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int n = db.delete("news_counter","cat = '"+cat+"'",null);
        return n;
    }
    public boolean insertCat (String cat, String c_like, String c_visit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cat", cat);
        contentValues.put("c_like", c_like);
        contentValues.put("c_visit", c_visit);

        long n = db.insert("news_counter", null, contentValues);
        Toast.makeText(context, n+cat, Toast.LENGTH_SHORT).show();
        return true;
    }

    public long updateVisit(String cat,String c_visit)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cat", cat);
        contentValues.put("c_visit", c_visit);

        long n = db.update("news_counter",contentValues,"cat = '"+cat+"'",null);
        return n;
    }
    public long updateLike(String cat,String c_like)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cat", cat);
        contentValues.put("c_like", c_like);

        long n = db.update("news_counter",contentValues,"cat = ? ", new String[] { cat });
        return n;
    }
    public int getLike(String cat) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select c_like from news_counter where cat='"+cat+"'", null );
        boolean s = res.moveToNext();
        Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
        int cnt=0;
        if(s)
            cnt = Integer.parseInt(res.getString(0));
        else
            cnt=0;
        return cnt;/* int cnt = Integer.parseInt(res.getString(1));
        return cnt;*/
    }
    public int getVisit(String cat) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select c_visit from news_counter where cat='"+cat+"'", null );
        boolean s = res.moveToNext();
        Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
        int cnt=0;
        if(s)
            cnt = Integer.parseInt(res.getString(0));
        else
            cnt=0;
        return cnt;
    }
}