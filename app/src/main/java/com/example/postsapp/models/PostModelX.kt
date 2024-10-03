package com.example.postsapp.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class PostModelX(
    var body: String?=null,
    @PrimaryKey
    var id: Int?=null,
    var title: String?=null,
    @Ignore
    var userId: Int?=null
)