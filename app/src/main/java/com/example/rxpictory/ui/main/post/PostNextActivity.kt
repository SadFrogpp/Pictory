package com.example.rxpictory.ui.main.post

import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import com.example.rxpictory.R
import com.example.rxpictory.databinding.ActivityPostNextBinding
import com.example.rxpictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_post_next.*
import kotlinx.android.synthetic.main.snippet_top_postbar.*
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageURI


class PostNextActivity(constract: PostConstract) : DataBindingActivity<ActivityPostNextBinding>(), PostConstract {

    override val layoutId: Int
        get() = R.layout.activity_post_next

    private val factory = PostViewModelFactory(constract, application)
    private val viewModel: PostViewModel by lazy {
        ViewModelProviders.of(this, factory).get(PostViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel

        postEditText.setHorizontallyScrolling(false)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        val path = intent.getStringExtra("path")
        if (path.contains("noPath")) postImage.setImageResource(R.drawable.ic_launcher_background)
        else postImage.imageURI = Uri.parse(path)

        postImage.imageBitmap = (postImage.drawable as BitmapDrawable).bitmap

        tvPrev.setOnClickListener {
            finish()
        }
    }
}
