package com.example.api_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.api_demo.RetrofitModel.data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<data> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        model = new ArrayList<>();
        customAdapter = new CustomAdapter(this, model);
        listView.setAdapter(customAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.Url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<String> call = retrofitApi.GetRetrofitApi();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.w("OnResponse", "OnResponse Function Call ");
                if (response.isSuccessful() && response.body() != null) {
//                    Log.w("OnResponseBody", response.body());
                    Gson gson = new Gson();
                    String modelResponse = response.body();

                    RetrofitModel dataModel = gson.fromJson(modelResponse, RetrofitModel.class);

                    model.addAll(dataModel.getData());
                    customAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Data Fetched", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Data not Fetch", Toast.LENGTH_SHORT).show();
            }
        });


    }
}