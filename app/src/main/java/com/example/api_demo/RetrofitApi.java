package com.example.api_demo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    String Url = "https://reqres.in/api/";
    @GET("users?page=2")
    Call<String> GetRetrofitApi();
}
