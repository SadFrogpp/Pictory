package com.example.rxpictory.ui.content

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.rxpictory.connect.Connecter
import com.example.rxpictory.model.ContentModel
import com.example.rxpictory.util.SingleLiveEvent
import com.example.rxpictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

class ContentViewModel(val app: Application): AndroidViewModel(app) {

    var _id =  MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val imagePath = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val imageName = MutableLiveData<String>()
    val likeCheck = MutableLiveData<Boolean>()
    val text = MutableLiveData<String>()
    val replyText = MutableLiveData<String>()
    val profilePath = MutableLiveData<String>()
    val replyPoint = MutableLiveData<String>().apply { value = "0" }

    val items = MutableLiveData<ContentModel>()

    val doReply = SingleLiveEvent<Any>()
    val doUserInfo = SingleLiveEvent<Any>()

    fun getContent() {
        Connecter.api.getContent(getToken(app.applicationContext), _id.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                _id.value = it.posts.postId
                username.value = it.posts.username
                imageName.value = it.posts.imageName
                text.value = it.posts.text
                id.value = it.posts.id
                imagePath.value = it.posts.imagePath
                profilePath.value = it.profilePath
                //replyPoint.value = it.comments.size.toString()
                //items.value = it
            }, {
                Log.d("ContentVM", it.message)
            })
    }

    fun postReply() {
        Connecter.api.postReply(getToken(getApplication()), _id.value!!, getData(replyText.value!!))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

            }, {

            })
    }

    fun goUserPage() {
        doUserInfo.call()
    }

    fun goReply() {
        doReply.call()
    }

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }
}