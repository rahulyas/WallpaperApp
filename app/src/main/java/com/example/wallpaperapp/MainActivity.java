package com.example.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wallpaperapp.Adapter.Adapter;
import com.example.wallpaperapp.Api.RequestManager;
import com.example.wallpaperapp.Model.Photo;
import com.example.wallpaperapp.Model.Search;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Photo> photos;
    private RecyclerView recyclerView;
    Adapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //hooks
        recyclerView=findViewById(R.id.recyclerview);
        searchView= findViewById(R.id.search_view);
        photos = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),photos);
        recyclerView.setAdapter(adapter);
        findphotos();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Image Name...", Toast.LENGTH_SHORT).show();
                }
                else{
                    getserachimage(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void getserachimage(String query) {
        RequestManager.getResponseListener().getImage(query,1,80).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                photos.clear();
                if(response.isSuccessful())
                {
                    photos.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    private void findphotos() {
        RequestManager.getResponseListener().getImage(1,80).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if(response.isSuccessful())
                {
                    photos.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }
}