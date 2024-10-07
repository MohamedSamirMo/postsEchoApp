package com.example.postsapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// retrofit normal code before using dagger hilt
//object ConnectRetrofit {
//    private val retrofit=Retrofit.Builder()
//        .baseUrl("https://jsonplaceholder.typicode.com/")
//        .addConverterFactory(GsonConverterFactory.create())
////        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//        .build()
//    val apiServices= retrofit.create(ApiServices::class.java)
//}