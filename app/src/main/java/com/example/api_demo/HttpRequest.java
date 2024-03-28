package com.example.api_demo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private final MainActivity mainActivity;

    public HttpRequest(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Override
    protected String doInBackground(String... strings) {
        String myUrl = strings[0];
        String result = "";
        try {
            URL url = new URL(myUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

            Log.d("MyHttpRequest doInBackground : ", String.valueOf(httpURLConnection.getResponseCode()));
            // to log the response message from your server after you have tried the request.
            Log.d("MyHttpRequest doInBackground : ", httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mainActivity.onResponse(result);
    }
}
