package com.example.android.searchcat;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient(Context context){
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ChuckerInterceptor.Builder(context).build())
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okhttpClient)
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
