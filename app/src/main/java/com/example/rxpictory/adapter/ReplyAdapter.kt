package com.example.rxpictory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rxpictory.R
import com.example.rxpictory.model.ReplyListModel
import org.jetbrains.anko.find

class ReplyAdapter(val models: ArrayList<ReplyListModel>): RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReplyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_reply_list, p0, false)
        return ReplyViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: ReplyViewHolder, p1: Int) = p0.bind(models[p1])

    inner class ReplyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val replyImage = itemView.find<ImageView>(R.id.item_reply_img)
        val replyName = itemView.find<TextView>(R.id.item_reply_name)
        val replyText = itemView.find<TextView>(R.id.item_reply_text)

        fun bind(model: ReplyListModel) {
            Glide.with(replyImage)
                .load(model.imageUrl).into(replyImage)
            replyName.text = model.name
            replyText.text = model.replyText
        }
    }
}