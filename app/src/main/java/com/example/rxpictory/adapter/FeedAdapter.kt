package com.example.rxpictory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rxpictory.databinding.ItemFeedRecyclerviewBinding
import com.example.rxpictory.model.FeedModel
import com.example.rxpictory.ui.main.feed.FeedViewModel

class FeedAdapter(val viewModel: FeedViewModel) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    var item = arrayListOf<FeedModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedViewHolder {
        val binding = ItemFeedRecyclerviewBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return FeedViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(p0: FeedViewHolder, p1: Int) = p0.bind()

    inner class FeedViewHolder(val binding: ItemFeedRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.index = adapterPosition
            binding.viewModel = viewModel
            binding.feedModel = item[adapterPosition]
        }
    }
}