package com.example.mahima.slidedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahima.slidedemo.dbhelper.DBHelper;
import com.example.mahima.slidedemo.networkpackage.NetworkClass;
import com.example.mahima.slidedemo.networkpackage.NetworkInterface;
import com.example.mahima.slidedemo.utility.SharedManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class news extends AppCompatActivity implements NetworkInterface {

    TextView t1,t2;
    //Button btn;
    ArrayList<String> desc;
    String title,cat;
    SQLiteDatabase db;
    String table_name = "news_counter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);

        SharedManager sm = new SharedManager(this);
        title = sm.getData("news_title","def");
        cat = sm.getData("cat_name","def");

        //Toast.makeText(this,title,Toast.LENGTH_LONG).show();
        String data = convertdata();
        NetworkClass task = new NetworkClass(this, this);
        task.execute("getnewsdesc.php",data);

        t1.setText(title);

        updateTable();
    }
    String convertdata()
    {
        String data = "";
        try {

            data = URLEncoder.encode("newtitle","UTF-8")+"="+URLEncoder.encode(title,"UTF-8")+"&"+
            URLEncoder.encode("catname","UTF-8")+"="+URLEncoder.encode(cat,"UTF-8");
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        return data;
    }

    @Override
    public void writeData(String response) {
        //t2.setText(response);
        //Toast.makeText(this,response,Toast.LENGTH_LONG).show();

        if(response.equals("fail"))
        {
            Toast.makeText(this,"abc",Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(this,response,Toast.LENGTH_LONG).show();

            desc = new ArrayList<>();
            try{
                JSONArray jsonarray=new JSONArray(response);
                for(int i=0;i<jsonarray.length();i++)
                {
                    JSONObject obj=jsonarray.getJSONObject(i);
                    desc.add(obj.getString("desc"));
                    t2.setText(response);
                }
            }catch (JSONException e){
                Toast.makeText(this,"result parcing error",Toast.LENGTH_LONG).show();
            }
        }
    }

    void updateTable()
    {
        DBHelper dbhelper = new DBHelper(this);
        int cnt = dbhelper.getVisit(cat);
        Toast.makeText(this, cnt+" values from "+cat, Toast.LENGTH_SHORT).show();
        cnt++;
        long n = dbhelper.updateVisit(cat,cnt+"");
        Toast.makeText(this, "update data : "+n, Toast.LENGTH_SHORT).show();
    }
}
