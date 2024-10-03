package com.example.postsapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.postsapp.models.CommentsModel
import com.example.postsapp.models.PostModelX


@Database(entities = [PostModelX::class, CommentsModel::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase()  {
    abstract fun getDao(): MyDao
    companion object {
        lateinit var myDatabase: MyDatabase
        fun init(context: Context){
            myDatabase =Room.databaseBuilder(context, MyDatabase::class.java
                ,"myDatabase")
//                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

    }


}