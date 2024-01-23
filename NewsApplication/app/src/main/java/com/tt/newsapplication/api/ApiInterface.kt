package com.tt.newsapplication.api

import android.provider.Settings
import com.tt.newsapplication.model.NewsListModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.HashMap

interface ApiInterface {
    @GET("v2/everything")
     fun getNews(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsListModel>






}