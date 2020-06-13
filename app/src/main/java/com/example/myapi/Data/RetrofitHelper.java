package com.example.myapi.Data;

import com.example.myapi.Models.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitHelper
{
    @GET("/v2/top-headlines")
    Call<NewsModel> getNews(
            @Query ( "country" ) String country,
            @Query ( "category" ) String category,
            @Query ( "apiKey" ) String apiKey);
}
