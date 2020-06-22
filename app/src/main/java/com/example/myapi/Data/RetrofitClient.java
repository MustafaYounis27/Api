package com.example.myapi.Data;


import com.example.myapi.Models.NewsModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    private static RetrofitClient retrofitClient;
    private final static String base_URL="https://newsapi.org";
    private RetrofitHelper retrofitHelper;

    private RetrofitClient()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        retrofitHelper = retrofit.create ( RetrofitHelper.class );
    }

    static public RetrofitClient getInstance()
    {
        if (retrofitClient==null)
        {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Call<NewsModel> getNews(String country, String category, String apiKey )
    {
        return retrofitHelper.getNews ( country, category, apiKey );
    }
}
