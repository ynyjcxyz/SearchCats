package com.example.android.searchcat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editSearch;
    ArrayList<String> arraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTags();

//      Generate sample data
//        String[] tagsArray = new String[]{"Lion", "Tiger", "Dog",
//                "Cat", "Tortoise", "Rat", "Elephant", "Fox",
//                "Cow", "Donkey", "Monkey"};

        // Locate the ListView in listview_main.xml
//        list = (ListView) findViewById(R.id.listview);
//        for (Object o : tagsArray) {
//            AnimalNames animalNames = new AnimalNames((String) o);
//            // Binds all strings into an array
//            arraylist.add(animalNames);
//        }


        // Pass results to ListViewAdapter Class
//        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
//        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
//        editSearch = (SearchView) findViewById(R.id.search_view);
//        editSearch.setOnQueryTextListener(this);

    }

    private void getTags() {
        Call<List<String>> call = RetrofitClient.getInstance(getApplicationContext()).getMyApi().getTags("tags");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                List<String> tagsList = response.body();
                assert tagsList != null;
                arraylist.addAll(tagsList);

                list = findViewById(R.id.listview);
                adapter = new ListViewAdapter(MainActivity.this, arraylist);
                list.setAdapter(adapter);

                editSearch = findViewById(R.id.search_view);
                editSearch.setOnQueryTextListener(MainActivity.this);

                list.setOnItemClickListener((parent, view, position, id)
                        -> editSearch.setQuery(adapter.getItem(position), true));
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                throw new RuntimeException(t);
//                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Toast.makeText(MainActivity.this, "you choose:" + query, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, CatsListView.class);
        intent.putExtra("query",query);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            list.clearTextFilter();
        } else {
            adapter.filter(newText);
        }
        return true;
//        adapter.filter(newText);
//        return false;
    }
}