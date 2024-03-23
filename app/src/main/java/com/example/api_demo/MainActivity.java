package com.example.api_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.api_demo.RetrofitModel.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Data> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        model = new ArrayList<>();
        customAdapter = new CustomAdapter(this, model);
        listView.setAdapter(customAdapter);

//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClient.addInterceptor(loggingInterceptor);

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
//                    Gson gson = new Gson();
                    String modelResponse = response.body();
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        RetrofitModel dataModel = objectMapper.readValue(modelResponse, RetrofitModel.class);

                        model.addAll(dataModel.getData());
                        customAdapter.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "Data Fetched", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Data not Fetch", Toast.LENGTH_SHORT).show();
                Log.e("OnFailure", "Failed to fetch data: " + t.getMessage());
            }
        });
    }
}