package com.example.rxpictory.ui.main.feed

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.model.FeedModel
import com.example.rxpictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FeedViewModel(val app: Application) : AndroidViewModel(app) {
    val model = MutableLiveData<ArrayList<FeedModel>>()

    fun getFeed() {
        api.getFeed(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({ model.value = it }, {
                Log.d("오류", it.message)
            })
    }
}