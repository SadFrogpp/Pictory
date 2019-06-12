package com.example.rxpictory.ui.follower

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.rxpictory.adapter.FollowerAdapter
import com.example.rxpictory.model.FollowerModel

@BindingAdapter("followerItem")
fun RecyclerView.setBindItem(followerModel: LiveData<ArrayList<FollowerModel>>) {
    val adapter: FollowerAdapter = adapter as FollowerAdapter
    followerModel.value?.let {adapter.item = it}
}

@BindingAdapter("followerImage")
fun setFollowerImage(imageView: ImageView, imagePath: String?) {
    Glide.with(imageView.context).load(imagePath).centerCrop().into(imageView)
    Log.d("FollowerBindingAdapter", "이미지 불러오기")
}