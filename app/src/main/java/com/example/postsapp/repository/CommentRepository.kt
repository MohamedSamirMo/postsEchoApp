package com.example.postsapp.repository

import com.example.postsapp.models.CommentsModel
import com.example.postsapp.network.ConnectRetrofit
import com.example.postsapp.roomdatabase.MyDatabaseComment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CommentRepository {

    private val appContext = com.example.postsapp.App()
    private val compositeDisposable = CompositeDisposable()

    fun getComment(
        postId: Int,
        onCommentSuccess: (List<CommentsModel>) -> Unit,
        onCommentError: (Throwable) -> Unit
    ) {
        getCommentRemote(postId, onCommentSuccess, onCommentError)
    }

    fun getCommentRemote(
        postId: Int,
        onCommentSuccess: (List<CommentsModel>) -> Unit,
        onCommentError: (Throwable) -> Unit
    ) {
        ConnectRetrofit.apiServices.getCommentsByPostId(postId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<CommentsModel>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    getFromCache(postId, onCommentSuccess, onCommentError)
                    onCommentError.invoke(e)
                }

                override fun onSuccess(t: List<CommentsModel>) {
                    insertComment(t)
                    onCommentSuccess.invoke(t)  // تمرير التعليقات المحملة إلى الواجهة
                }
            })
    }

    private fun insertComment(list: List<CommentsModel>) {
        MyDatabaseComment.getDatabase(appContext)
            .getDao()
            .insertComments(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {
                    // إدخال ناجح
                }

                override fun onError(e: Throwable) {
                    // معالجة خطأ الإدخال
                }
            })
    }

    fun getFromCache(
        postId: Int,
        onCommentSuccess: (List<CommentsModel>) -> Unit,
        onCommentError: (Throwable) -> Unit
    ) {
        MyDatabaseComment.getDatabase(appContext)
            .getDao()
            .getCommentsByPostId(postId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<CommentsModel>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    onCommentError.invoke(e)  // التعامل مع خطأ الكاش
                }

                override fun onSuccess(t: List<CommentsModel>) {
                    onCommentSuccess.invoke(t)  // تمرير التعليقات من الكاش إلى الواجهة
                }
            })
    }
}
