package com.example.rxpictory.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxpictory.R
import com.example.rxpictory.ui.main.post.PostNextActivity
import kotlinx.android.synthetic.main.item_photo.view.*

class PostAdapter(private val context: Context,
                  private val albumList: ArrayList<String>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run {outHeight to outWidth}
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            Log.e("Post", "이미지 불러오기")
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private fun decodeSampledBitmapFromURI(path: String, reqWidth: Int, reqHeight: Int) : Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, this)

            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(path, this)
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val img = decodeSampledBitmapFromURI(albumList[position], 300, 300)

        if(img !=null) holder.itemView.photo.setImageBitmap(img)
        else holder.itemView.photo.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.photo.setOnClickListener {
            val intent = Intent(context, PostNextActivity::class.java)
            if(img != null) intent.putExtra("path", albumList[position])
            else intent.putExtra("path", "noPath")
            context.startActivity(intent)
        }
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = albumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(parent)

    class PostViewHolder(parentView: View) : RecyclerView.ViewHolder(
        LayoutInflater.from(parentView.context).inflate(R.layout.item_photo, null, false))
}