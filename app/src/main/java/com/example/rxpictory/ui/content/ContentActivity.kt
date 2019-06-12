package com.example.rxpictory.ui.content

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.example.rxpictory.R
import com.example.rxpictory.ui.reply.ReplyActivity
import com.example.rxpictory.ui.yourPage.YourPageActivity
import com.example.rxpictory.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class ContentActivity : DataBindingActivity<com.example.rxpictory.databinding.ActivityContentBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_content

    private val viewModel: ContentViewModel by lazy {
        ViewModelProviders.of(this).get(ContentViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel._id.value = intent.getStringExtra("_id")
        Log.d("activitycon", "" + intent.getStringExtra("_id"))
        viewModel.doReply.observe(this, Observer { startActivity<ReplyActivity>("_id" to viewModel._id.value) })
        viewModel.doUserInfo.observe(this, Observer { startActivity<YourPageActivity>("userID" to viewModel.id.value) })
        viewModel.getContent()
        Log.d("acvititycon", "" + viewModel.imagePath.value)
    }
}
