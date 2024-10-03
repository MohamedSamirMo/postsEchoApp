package com.example.postsapp.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.postsapp.models.CommentsModel
import com.example.postsapp.models.PostModelX
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MyDao {
    @Insert
    fun insertPost(list: List<PostModelX>) : Completable

    @Query("select * from PostModelX")
    fun getAllPosts(): Single<List<PostModelX>>

    @Insert
    fun insertComment(list: List<CommentsModel>): Completable







}