package com.tt.newsapplication.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {
    val url = "https://newsapi.org/"
    val client: ApiInterface
        get() {
            val gson: Gson = GsonBuilder().setLenient().create()

            val mHttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val mOkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .connectTimeout(80, TimeUnit.SECONDS) // Set your connect timeout here (e.g., 30 seconds)
                .readTimeout(80, TimeUnit.SECONDS)
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    
}