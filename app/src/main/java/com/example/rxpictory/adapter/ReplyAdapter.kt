package com.example.rxpictory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rxpictory.R
import com.example.rxpictory.databinding.ItemReplyListBinding
import com.example.rxpictory.model.ReplyListModel
import com.example.rxpictory.ui.reply.ReplyViewModel
import org.jetbrains.anko.find

class ReplyAdapter(val viewModel: ReplyViewModel): RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {

    var items = arrayListOf<ReplyListModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReplyViewHolder {
        val binding = ItemReplyListBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ReplyViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ReplyViewHolder, p1: Int) = p0.bind()

    inner class ReplyViewHolder(private val binding: com.example.rxpictory.databinding.ItemReplyListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.vm = viewModel
            binding.index = adapterPosition
            binding.model = items[adapterPosition]
        }
    }
}