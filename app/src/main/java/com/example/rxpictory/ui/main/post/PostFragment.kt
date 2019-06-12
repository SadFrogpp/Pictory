package com.example.rxpictory.ui.main.post

import android.annotation.SuppressLint
import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxpictory.R
import com.example.rxpictory.adapter.PostAdapter
import com.example.rxpictory.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment() {

    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllImages((activity as MainActivity?)!!)

        post_recycler.apply {
            adapter = postAdapter
            layoutManager = GridLayoutManager(context, 3)
            smoothScrollToPosition(0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    @SuppressLint("GridView")
    private fun getAllImages(activity: Activity) {
        val uri: Uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var cursor: Cursor?
        val columnIndexData: Int
        val albumList = ArrayList<String>()

        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        cursor = activity.contentResolver.query(uri, projection, null, null, null)

        columnIndexData = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor!!.moveToNext()) {
            albumList.add(cursor!!.getString(columnIndexData))
        }
        albumList.reverse()
        postAdapter = PostAdapter(context!!, albumList)
        postAdapter.notifyDataSetChanged()
    }
}