package com.example.mahima.slidedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

    public class aboutus extends Fragment {

        Button btn;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            View v = inflater.inflate(R.layout.fragment_aboutus, null);
            return v;
        }

    }



