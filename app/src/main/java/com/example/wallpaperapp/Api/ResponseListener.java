package com.example.wallpaperapp.Api;

import static com.example.wallpaperapp.Api.RequestManager.API;

import com.example.wallpaperapp.Model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ResponseListener {

    String BASE_URl="https://api.pexels.com/v1/";

    @Headers("Authorization: "+API)
    @GET("curated")
    Call<Search> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+API)
    @GET("search")
    Call<Search> getImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );

}
