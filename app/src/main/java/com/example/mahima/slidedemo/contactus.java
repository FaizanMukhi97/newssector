package com.example.mahima.slidedemo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

    public class contactus extends Fragment {

        Button btn;
        @Nullable
        @Override

        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_contactus, null);

            btn=(Button)v.findViewById(R.id.btncall);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String Number="9512488398";

                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + Number));
                    startActivity(intent);
                }
            });
            return v;

        }
    }

