package com.example.api_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Model.data> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        model = new ArrayList<>();
        customAdapter = new CustomAdapter(this, model);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<Model> call = retrofitApi.GetRetrofitApi();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                Log.w("OnResponse", "OnResponse Function Call ");
                if (response.isSuccessful() && response.body() != null) {
                    Model modelResponse = response.body();
                    model.addAll(modelResponse.getData());
                    customAdapter.notifyDataSetChanged();
                    listView.setAdapter(customAdapter);
                    Toast.makeText(MainActivity.this, "Data Fetched", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Data not Fetch", Toast.LENGTH_SHORT).show();
            }
        });
    }
}