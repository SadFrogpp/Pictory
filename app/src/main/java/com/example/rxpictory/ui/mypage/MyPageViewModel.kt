package com.example.rxpictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.rxpictory.connect.Connecter
import com.example.rxpictory.model.Posts
import com.example.rxpictory.model.UserModel
import com.example.rxpictory.util.LifecycleCallback
import com.example.rxpictory.util.SingleLiveEvent
import com.example.rxpictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(val app: Application) : AndroidViewModel(app) {

    val username = MutableLiveData<String>()
    val profileIMG = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    val imageName = MutableLiveData<List<String>>()
    val imagePath = MutableLiveData<List<String>>()
    val _id = MutableLiveData<String>()
    val profilePath = MutableLiveData<String>()

    val items = MutableLiveData<ArrayList<Posts>>()

    val model=MutableLiveData<UserModel>()

    val postPointText = MutableLiveData<String>().apply {value = "0" }
    val followingPointText = MutableLiveData<String>().apply { value = "0" }
    val followerPoiontText = MutableLiveData<String>().apply { value = "0" }

    val doEditEvent = SingleLiveEvent<Any>()
    val goFollowingListEvent = SingleLiveEvent<Any>()
    val goFollowerListEvent = SingleLiveEvent<Any>()
    val addImageEvent = SingleLiveEvent<Any>()
    val doShowContent = SingleLiveEvent<Any>()

    fun getMypage() {
        var post: ArrayList<Posts>
        Connecter.api.getUserInfo(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("MypageVM", "" + it.user.username)
                username.value = it.user.username
                profileIMG.value = it.user.profileIMG
                id.value = it.user.id
                birth.value = it.user.birth
                imageName.value = it.user.imageName
                imagePath.value = it.user.imagePath
                profilePath.value = it.user.profilePath
                postPointText.value = it.posts.size.toString()
                followerPoiontText.value = it.user.follower.size.toString()
                followingPointText.value = it.user.following.size.toString()
                post = it.posts
                items.value = post
                Log.d("Mypost", items.value!![0].imagePath)
            }, {
                Log.d("MyPageVM", it.message)
            })
    }

//    fun getMyLike() {
//        Connecter.api.getMyLike(getToken(app.applicationContext))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                items2.value = it
//            }, {
//                Log.d("에러", "내가 좋아요 누른 글 불러오기 실패")
//            })
//    }

    fun toEditProfile() {
//        Connecter.api.profileEdit(
//            getToken(getApplication()),
//            getData(username.value!!),
//            getData(birth.value!!))
//            .enqueue(object: Callback<Unit> {
//                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                    Log.d("profileEdit", "ok")
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//                    Log.d("profileEdit", "error")
//                }
//            })
        doEditEvent.call()
    }

    fun toShowFollower() {
        goFollowerListEvent.call()
    }
    fun toShowFollowing() = goFollowingListEvent.call()
    fun selectProfileImage() = addImageEvent.call()

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }


    fun goContent(index: Int) {
        _id.value = items.value!![index].postId
        doShowContent.call()
    }
}