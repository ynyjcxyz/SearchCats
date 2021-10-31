package com.example.android.searchcat;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

public class InfoLoader extends AsyncTaskLoader<List<CatsInfo>> {
    public static final String LOG_TAG = AsyncTaskLoader.class.getSimpleName();
    private final String query;

    public InfoLoader(@NonNull Context context, String queryString) {
        super(context);
        query = queryString;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"This is onStartLoading() method");
        forceLoad();
    }

    @Nullable
    @Override
    public List<CatsInfo> loadInBackground() {
        Log.i(LOG_TAG,"This is loadInBackground() method");
        try{
            List<CatsInfo> catsInfo = CatsRepository.fetch(query);
            return catsInfo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
