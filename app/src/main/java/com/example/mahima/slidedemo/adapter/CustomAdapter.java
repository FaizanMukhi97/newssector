package com.example.mahima.slidedemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mahima.slidedemo.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<String> title;
    ArrayList<String> sdesc;

    public CustomAdapter(ArrayList<String>title,ArrayList<String>sdesc)
    {
        this.title = title;
        this.sdesc = sdesc;
    }
    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v;
        v = inflater.inflate(R.layout.designcat,null);

        TextView txTitle = v.findViewById(R.id.title);
        TextView txSDec = v.findViewById(R.id.shdec);

        txTitle.setText(title.get(position));
        txSDec.setText(sdesc.get(position));

        return v;
    }
}
