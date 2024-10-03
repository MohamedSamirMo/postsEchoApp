package com.example.postsapp.repository

import android.util.Log
import com.example.postsapp.models.PostModelX
import com.example.postsapp.network.ConnectRetrofit
import com.example.postsapp.roomdatabase.MyDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class PostRepository {
    private val compositeDisposable = CompositeDisposable()

    fun getPost(    onPostSuccess: (List<PostModelX>) -> Unit,
                    onPostError: (Throwable) -> Unit) {
       return getPostRemote(onPostSuccess, onPostError)
    }

    fun getPostRemote(
        onPostSuccess: (List<PostModelX>) -> Unit,
        onPostError: (Throwable) -> Unit
    ) {
        ConnectRetrofit.apiServices.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<PostModelX>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    getFromCache(onPostSuccess, onPostError)
                    onPostError.invoke(e)

                }

                override fun onSuccess(t: List<PostModelX>) {
                    onPostSuccess.invoke(t)
                    insertPost(t)

                }
            })
    }

    private fun insertPost(list: List<PostModelX>) {
        MyDatabase.myDatabase.getDao().insertPost(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                    Log.d("InsertPost", "Posts inserted successfully")
                }

                override fun onError(e: Throwable) {
                    Log.e("InsertPost", "Error inserting posts: ${e.message}")
                }
            })
    }

    fun getFromCache( onPostSuccess: (List<PostModelX>) -> Unit,
                      onPostError: (Throwable) -> Unit
    ) {
        MyDatabase.myDatabase.getDao()
            .getAllPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<PostModelX>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)

                }

                override fun onError(e: Throwable) {
                  onPostError.invoke(e)
                }

                override fun onSuccess(t: List<PostModelX>) {
                    onPostSuccess.invoke(t)
                }
            })
    }

}
