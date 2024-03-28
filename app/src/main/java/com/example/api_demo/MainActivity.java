package com.example.api_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.api_demo.RetrofitModel.Data;

import java.util.ArrayList;

import org.json.JSONArray;
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

        String myUrl = "https://reqres.in/api/users?page=2";

        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute(myUrl);

    }

    public void onResponse(String response) {
        try {

            Log.d("onResponse : ", response);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String firstName = object.getString("first_name");
                String lastName = object.getString("last_name");
                String email = object.getString("email");
                String image = object.getString("avatar");
                Data modelData = new Data(id, email, firstName, lastName, image);
                responseModel.add(modelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        customAdapter = new CustomAdapter(this, responseModel);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.create();

            }
        });
    }
}