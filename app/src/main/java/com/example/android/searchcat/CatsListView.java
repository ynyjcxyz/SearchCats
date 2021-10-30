package com.example.android.searchcat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.util.ArrayList;
import java.util.List;

public class CatsListView extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CatsInfo>> {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int DATA_LOADER_ID = 1;
    private InfoAdapter mAdapter;
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cats_view);

        mAdapter = new InfoAdapter(this, new ArrayList<CatsInfo>());

        ListView catsInfoListView = (ListView) findViewById(R.id.cats_list);
        emptyStateTextView = findViewById(R.id.empty_view);

        catsInfoListView.setEmptyView(emptyStateTextView);
        catsInfoListView.setAdapter(mAdapter);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG, "This is initLoader()");
            LoaderManager.getInstance(this).initLoader(DATA_LOADER_ID, null, this).forceLoad();
        } else {
            ProgressBar prgBar = (ProgressBar) findViewById(R.id.loading_bar);
            prgBar.setVisibility(View.GONE);
            emptyStateTextView.setText("No Internet Connection...");
        }
    }

    @NonNull
    @Override
    public Loader<List<CatsInfo>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i(LOG_TAG, "This is onCreateLoader() callback");

        Intent intent = getIntent();
        String queryString = intent.getStringExtra("query");
        return new InfoLoader(CatsListView.this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<CatsInfo>> loader, List<CatsInfo> data) {
        Log.i(LOG_TAG, "This is onLoadFinished() callback");
        ProgressBar prgBar = (ProgressBar) findViewById(R.id.loading_bar);
        prgBar.setVisibility(View.GONE);
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            emptyStateTextView.setText("No Data can be found...");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<CatsInfo>> loader) {
        mAdapter.clear();
    }
}