package com.example.wallpaperapp.Api;

import com.google.gson.internal.GsonBuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    private static Retrofit retrofit= null;
    public static final String API="563492ad6f917000010000019cd47f4df87d4e21b873e1c53c736331";

    public static ResponseListener getResponseListener()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(ResponseListener.BASE_URl).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit.create(ResponseListener.class);
    }

}
