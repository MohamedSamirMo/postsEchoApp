package com.example.postsapp.network

import com.example.postsapp.models.CommentsModel
import com.example.postsapp.models.PostModelX
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
@GET("posts")
fun getPosts(): Single<List<PostModelX>>


    @GET("comments")
    fun getCommentsByPostId(@Query("postId") postId: Int): Single<List<CommentsModel>>

}