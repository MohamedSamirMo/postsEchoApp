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
    // Room Database with Coroutines
    @Insert
   suspend fun insertPost(list: List<PostModelX>)

    @Query("select * from PostModelX")
    suspend fun getAllPosts(): List<PostModelX>

    @Insert
   suspend fun insertComment(list: List<CommentsModel>)

    @Insert
   suspend fun insertComments(list: List<CommentsModel>)

    @Query("select * from CommentsModel where postId=:postId")
    suspend fun getCommentsByPostId(postId: Int): List<CommentsModel>






}

// Room Database with Rxjava
//@Insert
//fun insertPost(list: List<PostModelX>) : Completable
//
//@Query("select * from PostModelX")
//fun getAllPosts(): Single<List<PostModelX>>
//
//@Insert
//fun insertComment(list: List<CommentsModel>): Completable
//
//@Insert
//fun insertComments(list: List<CommentsModel>): Completable
//
//@Query("select * from CommentsModel where postId=:postId")
//fun getCommentsByPostId(postId: Int): Single<List<CommentsModel>>
//

