package com.example.postsapp.di

import android.content.Context
import androidx.room.Room
import com.example.postsapp.network.ApiServices
import com.example.postsapp.roomdatabase.MyDao
import com.example.postsapp.roomdatabase.MyDatabase
import dagger.Module

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
object Module {


    @Singleton
    @Provides
    fun  getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun getApiServices(retrofit: Retrofit) :ApiServices{
      return  retrofit.create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun getMyDatabase(@ApplicationContext context: Context) :MyDatabase{
        return Room.databaseBuilder(context, MyDatabase::class.java
            ,"myDatabase")
//                .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun getMyDao(myDatabase: MyDatabase):MyDao{
        return myDatabase.getDao()
    }
}