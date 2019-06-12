package com.example.rxpictory.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.rxpictory.R
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.databinding.ItemFollwersBinding
import com.example.rxpictory.model.FollowerModel
import com.example.rxpictory.ui.follower.FollowerViewModel
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerAdapter(val viewModel: FollowerViewModel) :
    RecyclerView.Adapter<FollowerAdapter.FollowerRvViewHolder>() {

    var item = arrayListOf<FollowerModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FollowerRvViewHolder {
        val binding = com.example.rxpictory.databinding.ItemFollwersBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return FollowerRvViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(p0: FollowerRvViewHolder, p1: Int) = p0.bind()

    inner class FollowerRvViewHolder(private val binding: ItemFollwersBinding) : RecyclerView.ViewHolder(binding.root) {
        val followerWhether = itemView.find<Button>(R.id.follow_whether_btn)
        var followClicked: Boolean = true

        fun bind() {
            followClicked = viewModel.followerBool.value!!
            val followerName = viewModel.follwerName.value

            binding.vm = viewModel
            binding.index = adapterPosition
            binding.model = item[adapterPosition]


            if (viewModel.followerBool.value!!)
                followerWhether.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.blue_radius_square_view)
            else
                followerWhether.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.gray_radius_square_view)

            followerWhether.setOnClickListener {
                if (followClicked) {
                    api.cancelFollow(viewModel.followerPath.value!!, hashMapOf("followWhether" to false, "username" to followerName))
                        .enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                                it.background =
                                    ContextCompat.getDrawable(itemView.context, R.drawable.blue_radius_square_view)
                            }

                            override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                                Toast.makeText(itemView.context, "팔로우 취소 불가", Toast.LENGTH_SHORT)
                            }
                        })
                } else {
                    api.startFollow(viewModel.followerPath.value!!, hashMapOf("followWhether" to true, "username" to followerName))
                        .enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                                it.background =
                                    ContextCompat.getDrawable(itemView.context, R.drawable.gray_radius_square_view)
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