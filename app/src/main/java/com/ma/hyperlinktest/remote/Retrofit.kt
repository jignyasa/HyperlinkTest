package com.ma.hyperlinktest.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object{
        fun getRetrofit(): ApiService
        {
            return Retrofit.Builder()
                .baseUrl("http://www.splashbase.co/api/v1/images/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService::class.java)
        }
    }
}