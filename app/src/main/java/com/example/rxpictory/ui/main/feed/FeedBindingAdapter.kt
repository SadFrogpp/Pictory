package com.example.rxpictory.ui.main.feed

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rxpictory.adapter.FeedAdapter
import com.example.rxpictory.model.FeedModel


@BindingAdapter("feedData")
fun RecyclerView.setFeedData(data: LiveData<ArrayList<FeedModel>>) {
    val feedAdapter: FeedAdapter = adapter as FeedAdapter
    data.value?.let { feedAdapter.item = it }
}

@BindingAdapter("image", "errorImage")
fun setImageUrl(view: ImageView, uri: String?, error: Drawable) {
    Glide.with(view.context).load(uri).apply(RequestOptions()).into(view)
}