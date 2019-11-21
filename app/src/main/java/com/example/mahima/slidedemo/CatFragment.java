package com.example.mahima.slidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahima.slidedemo.adapter.CustomAdapter;
import com.example.mahima.slidedemo.networkpackage.NetworkClass;
import com.example.mahima.slidedemo.networkpackage.NetworkInterface;
import com.example.mahima.slidedemo.utility.SharedManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class CatFragment extends Fragment implements NetworkInterface {

    ListView ls;
    String name;

    ArrayList<String>title,desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.catlist,null);
        ls = view.findViewById(R.id.catls);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i;
                i=new Intent(getActivity(),news.class);

                SharedManager sm = new SharedManager(getActivity());
                sm.writeData("news_title",title.get(position));

                startActivity(i);

            }
        });

        SharedManager sm = new SharedManager(getActivity());
        name = sm.getData("cat_name","doesnot exists");

        if(name.equals("doesnot exists"))
        {
            Toast.makeText(getActivity(),"cant get list of news",Toast.LENGTH_LONG).show();
        }
        else
        {
            //fetch the news
            try {
                String data = "";

                data = URLEncoder.encode("catname","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");

            NetworkClass task = new NetworkClass(getActivity(), this);
                task.execute("getnewslist.php", data);
            }
            catch(Exception e)
            {
                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }
        return view;
    }

    @Override
    public void writeData(String response) {
        if(response.equals("fail"))
        {
            Toast.makeText(getActivity(),"cant get news data",Toast.LENGTH_LONG).show();
        }
        else
        {
            parsedata(response);
        }
    }
    void parsedata(String response)
    {
        //Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
        title = new ArrayList<>();
        desc = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                title.add(obj.getString("title"));
                desc.add(obj.getString("short"));
            }
        } catch (JSONException e) {
            Toast.makeText(getActivity(),"result parsing error...",Toast.LENGTH_LONG).show();
        }

        CustomAdapter ad = new CustomAdapter(title,desc);
        ls.setAdapter(ad);
    }
}