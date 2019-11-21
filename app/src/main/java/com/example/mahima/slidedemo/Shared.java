package com.example.mahima.slidedemo;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {


    Context cnt;
    public Shared(Context cnt)
    {
        this.cnt = cnt;
    }
    public void writedata(String key, String value)
    {
        SharedPreferences spf = cnt.getSharedPreferences("parking",cnt.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString(key,value);
        ed.commit();
    }
    public String getValue(String key, String value)
    {
        SharedPreferences spf = cnt.getSharedPreferences("parking",cnt.MODE_PRIVATE);
        return spf.getString(key,value);
    }
}
