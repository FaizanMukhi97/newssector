package com.example.mahima.slidedemo.networkpackage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mahima.slidedemo.utility.Constans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkClass extends AsyncTask<String,Void,Void> {

    Context cnt;
    String progressmsg;
    NetworkInterface in;

    ProgressDialog pd;

    public NetworkClass(Context cnt, NetworkInterface in)
    {
        this.cnt = cnt;
        this.in = in;
        this.progressmsg=progressmsg;
    }

    String msg;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(cnt);
        pd.setMessage(progressmsg);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        pd.show();
    }
    String surl="";

    @Override
    protected Void doInBackground(String... strings) {

        try {
            surl=Constans.ip+strings[0];
            URL url = new URL(Constans.ip+strings[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            con.setRequestMethod("POST");

            if(strings.length>1) {
                OutputStream os = con.getOutputStream();
                os.write(strings[1].getBytes());
            }
            InputStream is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            msg = br.readLine();
        }
        catch (Exception e)
        {
            msg = e.toString();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        pd.dismiss();
        in.writeData(msg);
        Toast.makeText(cnt,msg,Toast.LENGTH_SHORT).show();
        Log.d("URL:",surl);

        super.onPostExecute(aVoid);
    }
}