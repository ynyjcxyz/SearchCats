package com.example.android.searchcat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatsService {
    @GET("cats")
    Call<List<CatsInfo>> listRepos(@Query("tags") String parameterQuery);
}