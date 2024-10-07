package com.example.postsapp


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.postsapp.adapter.CommentAdapter
import com.example.postsapp.databinding.ActivityCommentsBinding
import com.example.postsapp.models.CommentsModel

import com.example.postsapp.mvvm.CommentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCommentsBinding.inflate(layoutInflater)
    }
    private val adapter: CommentAdapter by lazy {
        CommentAdapter()
    }
    val viewModel: CommentsViewModel by viewModels()
//    private val viewModel: CommentsViewModel by lazy {
//        ViewModelProvider(this)[CommentsViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Get postId from the Intent
        val postId = intent.getIntExtra("postId", 0)

        // Fetch comments using the postId
        viewModel.getComment(postId)


        // Set a click listener for the back button
        binding.back.setOnClickListener {
            onBackPressed()
        }


        // Observe commentsLiveData to update the adapter
        viewModel.commentsLiveData.observe(this, { it ->
            // Update the adapter with the comments
            adapter.Comments =it as ArrayList<CommentsModel>// Update the adapter with the comments
            binding.recComments.adapter = adapter

        })


    }
}
