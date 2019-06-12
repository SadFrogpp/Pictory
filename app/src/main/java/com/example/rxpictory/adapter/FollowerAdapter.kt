package com.example.rxpictory.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.rxpictory.R
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.model.FollowerModel
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerAdapter(val models: ArrayList<FollowerModel>, val followPath: String): RecyclerView.Adapter<FollowerAdapter.FollowerRvViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FollowerRvViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_follwers, p0, false)
        return FollowerRvViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: FollowerRvViewHolder, p1: Int) = p0.bind(models[p1])

    inner class FollowerRvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val followerImage = itemView.find<ImageView>(R.id.item_follower_img)
        val followerName = itemView.find<TextView>(R.id.item_follower_name)
        val followerWhether = itemView.find<TextView>(R.id.follow_whether)
        var followClicked: Boolean = true

        fun bind(model: FollowerModel){
            Glide.with(followerImage).load(model.imageUrl).into(followerImage)
            followClicked = model.followBool
            followerName.text = model.name

            if (model.followBool)
                followerWhether.background = ContextCompat.getDrawable(itemView.context, R.drawable.blue_radius_square_view)
            else
                followerWhether.background = ContextCompat.getDrawable(itemView.context, R.drawable.gray_radius_square_view)

            followerWhether.setOnClickListener {
                if (followClicked){
                    api.cancelFollow(followPath, hashMapOf("followWhether" to false, "username" to model.name)).enqueue(object: Callback<Unit> {
                        override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                            it.background = ContextCompat.getDrawable(itemView.context, R.drawable.blue_radius_square_view)
                        }

                        override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                            Toast.makeText(itemView.context, "팔로우 취소 불가", Toast.LENGTH_SHORT)
                        }
                    })
                } else {
                    api.startFollow(followPath, hashMapOf("followWhether" to true, "username" to model.name)).enqueue(object: Callback<Unit> {
                        override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                            it.background = ContextCompat.getDrawable(itemView.context, R.drawable.gray_radius_square_view)
                        }

                        override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                            Toast.makeText(itemView.context, "팔로우 불가", Toast.LENGTH_SHORT)
                        }
                    })
                }
                followClicked = followClicked.not()
            }
        }
    }
}