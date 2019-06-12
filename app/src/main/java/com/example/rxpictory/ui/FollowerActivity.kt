package com.example.rxpictory.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.rxpictory.R
import com.example.rxpictory.adapter.FollowerAdapter
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.model.FollowerModel
import kotlinx.android.synthetic.main.activity_follwer.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follwer)
        title_kind.text = intent.getStringExtra("title")
        item_people.layoutManager = LinearLayoutManager(this)
        item_people.adapter = FollowerAdapter(setFollowers(), intent.getStringExtra("title"))
    }

    fun setFollowers(): ArrayList<FollowerModel> {
        var followerData = ArrayList<FollowerModel>()

        api.getFollower(intent.getStringExtra("title")).enqueue(object: Callback<ArrayList<FollowerModel>>{
            override fun onResponse(
                call: Call<ArrayList<FollowerModel>>?,
                response: Response<ArrayList<FollowerModel>>?
            ) {
                followerData = response!!.body()!!
            }

            override fun onFailure(call: Call<ArrayList<FollowerModel>>?, t: Throwable?) {
                toast("팔로워를 가져올 수 없습니다.").show()
            }
        })

        return followerData
    }
}