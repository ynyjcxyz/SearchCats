package com.example.android.searchcat;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "https://cataas.com/";
    @GET("api/{tags}")
    Call<List<String>> getTags(@Path("tags")String tags);
}