package com.example.rxpictory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rxpictory.databinding.ItemMypageImageBinding
import com.example.rxpictory.model.Posts
import com.example.rxpictory.ui.mypage.MyPageViewModel

class MyPostAdapter(val viewModel: MyPageViewModel): RecyclerView.Adapter<MyPostAdapter.MyPostViewHolder>() {

    var item = arrayListOf<Posts>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        holder.bind()
    }
    override fun getItemCount(): Int = item.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        val binding =  ItemMypageImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPostViewHolder(binding)
    }

    inner class MyPostViewHolder(private val binding: com.example.rxpictory.databinding.ItemMypageImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.vm = viewModel
            binding.model = item[adapterPosition]
            binding.index = adapterPosition
        }
    }
}