package com.example.rxpictory.ui.follower

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.rxpictory.connect.Connecter
import com.example.rxpictory.model.FollowerModel
import com.example.rxpictory.util.SingleLiveEvent
import com.example.rxpictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowerViewModel(app: Application): AndroidViewModel(app) {

    val items = MutableLiveData<ArrayList<FollowerModel>>()
    val userID = MutableLiveData<String>()
    val followerPath = MutableLiveData<String>()
    val followerBool = MutableLiveData<Boolean>()
    val follwerName = MutableLiveData<String>()

    val doShowUser = SingleLiveEvent<Any>()

    fun setAdapterData() {
        Connecter.api.getFollower(getToken(getApplication()), userID.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                items.value = it
            }, {
                Log.d("FollowerVM", "error")
            })
    }

    fun goUserPage(index: Int) {
        userID.value = items.value!![index].userID
        doShowUser.call()
    }

}