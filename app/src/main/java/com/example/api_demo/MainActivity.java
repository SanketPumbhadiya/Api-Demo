package com.example.api_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.api_demo.RetrofitModel.Data;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Data> responseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        responseModel = new ArrayList<>();

        String url = "https://reqres.in/api/users?Page=2/";


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("onResponse", "onResponse function call");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jObject = jsonArray.getJSONObject(i);
                                int id = jObject.getInt("id");
                                String fName = jObject.getString("first_name");
                                String lName = jObject.getString("last_name");
                                String email = jObject.getString("email");
                                String image = jObject.getString("avatar");

                                Data modelData = new Data(id, email, fName, lName, image);
                                responseModel.add(modelData);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        customAdapter = new CustomAdapter(MainActivity.this, responseModel);
                        listView.setAdapter(customAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(stringRequest);
    }
}