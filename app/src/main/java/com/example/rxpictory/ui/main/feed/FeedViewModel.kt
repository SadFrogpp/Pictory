package com.example.rxpictory.ui.main.feed

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.model.FeedModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FeedViewModel(val app: Application) : AndroidViewModel(app) {
    val model = MutableLiveData<ArrayList<FeedModel>>()

    fun getFeed() {
        api.getFeed("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwdyI6InRlc3QiLCJpZCI6InRlc3QiLCJpYXQiOjE1NjAyNTg3MTUsImV4cCI6MTU2MDYxODcxNSwiaXNzIjoibWUiLCJzdWIiOiJ1c2VyX2luZm8ifQ.M6hbipbZnQm4SIdq0QJHfYfxqzsKj_jnEZIwEJs4Cw4")
            .subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({ model.value = it }, {
                Log.d("오류", "나면 자살함")
            })
    }
}