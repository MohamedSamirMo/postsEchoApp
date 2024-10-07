package com.example.postsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.postsapp.adapter.PostsAdapter
import com.example.postsapp.databinding.ActivityMainBinding
import com.example.postsapp.extentionfunction.gone
import com.example.postsapp.extentionfunction.visible
import com.example.postsapp.models.PostModelX
import com.example.postsapp.mvvm.PostsViewModel

import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    lateinit var binding:ActivityMainBinding
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
}
    val adapter: PostsAdapter by lazy {
        PostsAdapter()
    }
    val CompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
     // Extention hilt
    val viewModel:PostsViewModel by viewModels()
//    val viewModel:PostsViewModel
//    by lazy {
//     ViewModelProvider(this)[PostsViewModel::class.java]
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.progressBar.visible()
        viewModel.postliveData.observe(this, { it ->
            adapter.posts = it as ArrayList<PostModelX>
            binding.recposts.adapter = adapter
            binding.progressBar.gone()

        })

        viewModel.errorLiveData.observe(this, {
            binding.progressBar.gone()
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        adapter.onItemClickListener = object : PostsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Intent(this@MainActivity, CommentsActivity::class.java).apply {
                    putExtra("postId", position)
                    startActivity(this)
                }
            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable.clear()
    }
}
