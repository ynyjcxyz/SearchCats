package com.example.android.searchcat;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatsRepository {
    public static List<CatsInfo> fetch(String parameterQuery) throws IOException{

        Gson enhancedGson = new GsonBuilder()
                .registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY)
                .create();
        GsonConverterFactory factory = GsonConverterFactory.create(enhancedGson);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://cataas.com/api/")
                .addConverterFactory(factory)
                .build();

        CatsService service = retrofit.create(CatsService.class);

        return service.listRepos(parameterQuery).execute().body();
    }
}
