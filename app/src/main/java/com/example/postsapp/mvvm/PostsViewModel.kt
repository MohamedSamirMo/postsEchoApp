package com.example.postsapp.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.roomdatabase.MyDatabase
import com.example.postsapp.models.PostModelX
import com.example.postsapp.network.ConnectRetrofit
import com.example.postsapp.repository.PostRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PostsViewModel: ViewModel()  {

    private val compositeDisposable = CompositeDisposable()
    private val _postliveData = MutableLiveData<List<PostModelX>>()
    val postliveData get() = _postliveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData get() = _errorLiveData

    private val postRepository = PostRepository()

    init {
        getPost()
    }

    private fun getPost() {

        postRepository.getPost({listPost->
            _postliveData.value=listPost
        },{error->
            _errorLiveData.value=error.localizedMessage
        })
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
   // mvvm code
//   fun getPost() {
//       ConnectRetrofit.apiServices.getPosts()
//           .subscribeOn(Schedulers.io())
//           .observeOn(AndroidSchedulers.mainThread())
//           .subscribe(object : SingleObserver<List<PostModelX>> {
//               override fun onSubscribe(d: Disposable) {
//                   compositeDisposable.add(d)
//               }
//
//               override fun onError(e: Throwable) {
//                   _errorLiveData.value = e.localizedMessage
//                   getFromCache()
//               }
//
//               override fun onSuccess(t: List<PostModelX>) {
//                   insertPost(t)
//                   _postliveData.value = t
//               }
//           })
//   }
//
//private fun insertPost(list: List<PostModelX>) {
//    MyDatabase.myDatabase.getDao().insertPost(list)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(object : CompletableObserver {
//            override fun onSubscribe(d: Disposable) {
//                compositeDisposable.add(d)
//            }
//
//            override fun onComplete() {
//                Log.d("InsertPost", "Posts inserted successfully")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.e("InsertPost", "Error inserting posts: ${e.message}")
//            }
//        })
//}
//
//fun getFromCache() {
//    MyDatabase.myDatabase.getDao()
//        .getAllPosts()
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(object : SingleObserver<List<PostModelX>> {
//            override fun onSubscribe(d: Disposable) {
//                compositeDisposable.add(d)
//            }
//
//            override fun onError(e: Throwable) {
//                Log.e("GetFromCache", "Error getting posts from cache: ${e.message}")
//            }
//
//            override fun onSuccess(t: List<PostModelX>) {
//                _postliveData.value = t
//            }
//        })
//}