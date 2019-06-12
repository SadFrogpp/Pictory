package com.example.rxpictory.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rxpictory.R
import com.example.rxpictory.adapter.ReplyAdapter
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.model.ReplyListModel
import com.example.rxpictory.util.getToken
import kotlinx.android.synthetic.main.activity_reply.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyActivity(): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply)

        Glide.with(reply_img).load(intent.getStringExtra("imageUrl")).into(reply_img)
        reply_username.text = intent.getStringExtra("username")
        reply_content.text = intent.getStringExtra("postText")

        loadReplyList(intent.getIntExtra("postCode", 0))

        reply_post_btn.onClick {
            api.postReply(
                getToken(this@ReplyActivity), intent.getIntExtra("postCode", 0),
                hashMapOf("text" to reply_edit.text)).enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                    loadReplyList(intent.getIntExtra("postCode", 0))
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    toast("댓글을 달 수 없습니다.")
                }
            })
        }
    }

    fun loadReplyList(postCode: Int){
        api.getReply(postCode).enqueue(object: Callback<ArrayList<ReplyListModel>>{
            override fun onResponse(
                call: Call<ArrayList<ReplyListModel>>?,
                response: Response<ArrayList<ReplyListModel>>?
            ) {
                reply_rv.layoutManager = LinearLayoutManager(this@ReplyActivity)
                reply_rv.adapter = ReplyAdapter(response!!.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<ReplyListModel>>?, t: Throwable?) {
                toast("댓글들을 가져올 수 없습니다.").show()
            }
        })
    }
}