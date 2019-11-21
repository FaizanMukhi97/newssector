package com.example.mahima.slidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahima.slidedemo.networkpackage.NetworkClass;
import com.example.mahima.slidedemo.networkpackage.NetworkInterface;
import com.example.mahima.slidedemo.utility.SharedManager;

import org.json.JSONObject;

import java.net.URLEncoder;


@SuppressLint("ValidFragment")
public class profile1 extends Fragment implements View.OnClickListener, NetworkInterface {


    EditText ed1, ed2, ed3,ed4,ed5,ed6,ed7;
    boolean getprflag = true;
    Button btn;
    String sname, semail, spass,saddress,spnumber,scity,sbdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile1, null);

        ed1 = (EditText) v.findViewById(R.id.name);
        ed2 = (EditText) v.findViewById(R.id.email);
        ed4 = (EditText) v.findViewById(R.id.address);
        ed3 = (EditText) v.findViewById(R.id.password);
        ed5 = (EditText) v.findViewById(R.id.city);
        ed6 = (EditText) v.findViewById(R.id.pnumber);
        ed7 = (EditText) v.findViewById(R.id.bdate);
        btn = (Button) v.findViewById(R.id.update);

        btn.setOnClickListener(this);

        SharedManager sm = new SharedManager(getActivity());
        semail = sm.getData("email", "def");

        Toast.makeText(getActivity(),semail,Toast.LENGTH_LONG).show();

        String data = "";
        try {
            data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(semail, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(getActivity(), "url encoding problem in update", Toast.LENGTH_LONG).show();
        }

        NetworkClass task = new NetworkClass(getActivity(),this);
        task.execute("searchuser.php", data);

        return v;
    }

    public void onClick(View v) {
        Button temp = (Button) v;
        if (temp == btn) {
            update(v);
        }
    }

    @Override
    public void writeData(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        if (getprflag == true) {
            if (msg.equals("fail")) {
                Toast.makeText(getActivity(), "cant read profile", Toast.LENGTH_LONG).show();
                getActivity().finish();
                Intent i = new Intent(getActivity(), u_login.class);
                getActivity().startActivity(i);
            }
            else {
                try {
                    JSONObject obj = new JSONObject(msg);

                    ed1.setText(obj.getString("Name"));
                    ed2.setText(obj.getString("Email_id"));
                    ed3.setText(obj.getString("Password"));
                    ed4.setText(obj.getString("Address"));
                    ed5.setText(obj.getString("City"));
                    ed6.setText(obj.getString("Phone_No"));
                    ed7.setText(obj.getString("B_date"));

                    Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), "json parsing error...", Toast.LENGTH_SHORT).show();
                }
                getprflag = false;
            }

        }

        if (getprflag == false) {
            if (msg.equals("success")) {
                Toast.makeText(getActivity(), "profile successfully updated", Toast.LENGTH_LONG).show();
            } else if (msg.equals("fail")) {
                Toast.makeText(getActivity(), "updation fail", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    void get() {
        sname = ed1.getText().toString();
        semail = ed2.getText().toString();
        spass = ed3.getText().toString();
        saddress=ed4.getText().toString();
        scity=ed5.getText().toString();
        spnumber=ed6.getText().toString();
        sbdate=ed7.getText().toString();

    }
    public void update(View v) {
        //put validation
        NetworkClass task = new NetworkClass(getActivity(), this);
        String data = "";
        get();

        try {
            data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(sname, "UTF-8") + "&" +
                    URLEncoder.encode("Email_id", "UTF-8") + "=" + URLEncoder.encode(semail, "UTF-8") + "&" +
                    URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(spass, "UTF-8") + "&" +
                    URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(saddress, "UTF-8") + "&" +
                    URLEncoder.encode("City", "UTF-8") + "=" + URLEncoder.encode(scity, "UTF-8") + "&" +
                    URLEncoder.encode("Phone_No", "UTF-8") + "=" + URLEncoder.encode(spnumber, "UTF-8") + "&" +
                    URLEncoder.encode("B_date", "UTF-8") + "=" + URLEncoder.encode(sbdate, "UTF-8");
            task.execute("updateuser.php",data);
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), "url endcoding problem in update ", Toast.LENGTH_SHORT).show();
        }
    }
}


