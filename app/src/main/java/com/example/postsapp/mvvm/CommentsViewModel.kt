package com.example.postsapp.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.models.CommentsModel
import com.example.postsapp.repository.CommentRepository

class CommentsViewModel : ViewModel() {

    private val _commentsLiveData = MutableLiveData<List<CommentsModel>>()
    val commentsLiveData get() = _commentsLiveData
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData get() = _errorLiveData
    private val commentRepository = CommentRepository()

    fun getComment(postId: Int) {
        commentRepository.getComment(postId, { commentsList ->
            _commentsLiveData.value = commentsList
        }, { error ->
            _errorLiveData.value = error.localizedMessage
        })
    }

    override fun onCleared() {
        super.onCleared()
        // لا حاجة لإلغاء compositeDisposable هنا لأن الـ Repository يتعامل معه
    }
}

// mvvm code
//fun getComment(postId: Int) {
//    ConnectRetrofit.apiServices.getCommentsByPostId(postId)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(object : SingleObserver<List<CommentsModel>> {
//            override fun onSubscribe(d: Disposable) {
//                compositeDisposable.add(d)
//            }
//
//            override fun onError(e: Throwable) {
//                _errorLiveData.value = e.localizedMessage
//                getFromCache(postId)
//            }
//
//            override fun onSuccess(t: List<CommentsModel>) {
//                _commentsLiveData.value = t
//                insertComment(t)
//            }
//        })
//}
//
//private fun insertComment(list: List<CommentsModel>) {
//    MyDatabaseComment.getDatabase(
//        Context
//    )
//        .getDao()
//        .insertComments(list)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(object : CompletableObserver {
//            override fun onSubscribe(d: Disposable) {
//                compositeDisposable.add(d)
//            }
//
//            override fun onComplete() {
//                // Handle success if needed
//            }
//
//            override fun onError(e: Throwable) {
//                _errorLiveData.value = e.localizedMessage // Handle insert error
//            }
//        })
//}
//
//fun getFromCache(postId: Int) {
//    MyDatabaseComment.getDatabase(
//
//        Context
//    )
//        .getDao()
//        .getCommentsByPostId(postId)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(object : SingleObserver<List<CommentsModel>> {
//            override fun onSubscribe(d: Disposable) {
//                compositeDisposable.add(d)
//            }
//
//            override fun onError(e: Throwable) {
//                _errorLiveData.value = e.localizedMessage // Handle fetch error
//            }
//
//            override fun onSuccess(t: List<CommentsModel>) {
//                _commentsLiveData.value = t
//            }
//        })
//}
