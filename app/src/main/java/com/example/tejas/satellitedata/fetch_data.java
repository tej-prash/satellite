package com.example.tejas.satellitedata;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Tejas on 05-Aug-17.
 */


public class fetch_data extends AsyncTask<Void,Void,Void> {
    public static int status=0;
    public static int avai=0;
    public static int line=0;
    private Context context;
    fetch_data(Context c, int s){
        this.status=s;
        this.context=c;
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... params) {
        StringBuffer buff = new StringBuffer("");
        String link = "http://www.google.co.in";
        URL url = null;
        try {
                Log.d("FETCH DATA","Class is called");
            if(display_data.no_internet==0) {
                url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                line = rd.read();

                if (line == -1) {
                    status = 1;
                    Log.d("FETCH DATA", "FIle being updated");
                } else {
                    status = 0;
                }
                rd.close();
                inputStream.close();
                connection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
