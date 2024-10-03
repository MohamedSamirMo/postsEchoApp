package com.example.postsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CommentsModel")
data class CommentsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)