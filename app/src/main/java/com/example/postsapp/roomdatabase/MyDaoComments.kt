package com.example.postsapp.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postsapp.models.CommentsModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MyDaoComments {

    @Insert
    fun insertComments(list: List<CommentsModel>): Completable

    @Query("select * from CommentsModel where postId=:postId")
    fun getCommentsByPostId(postId: Int): Single<List<CommentsModel>>

}
