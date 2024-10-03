package com.example.postsapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.postsapp.models.CommentsModel
import com.example.postsapp.models.PostModelX

@Database(entities = [CommentsModel::class, PostModelX::class], version = 1, exportSchema = false)
abstract class MyDatabaseComment : RoomDatabase() {
    abstract fun getDao(): MyDaoComments

    companion object {
        @Volatile
        private var INSTANCE: MyDatabaseComment? = null

        fun getDatabase(context: Context): MyDatabaseComment {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabaseComment::class.java,
                    "myDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
