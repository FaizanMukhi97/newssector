package com.example.mahima.slidedemo.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedManager {
    Context cnt;
    SharedPreferences spf;

    public SharedManager(Context cnt)
    {
        this.cnt = cnt;
        spf = cnt.getSharedPreferences("news_app",cnt.MODE_PRIVATE);
    }
    public void writeData(String key,String value)
    {
        SharedPreferences.Editor ed = spf.edit();
        ed.putString(key,value);
        ed.commit();
    }
    public String getData(String key,String def)
    {
        String data="";
        data = spf.getString(key,def);
        return  data;
    }
}
