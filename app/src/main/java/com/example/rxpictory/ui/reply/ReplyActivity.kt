package com.example.rxpictory.ui.reply

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.rxpictory.R
import com.example.rxpictory.adapter.ReplyAdapter
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.model.ReplyListModel
import com.example.rxpictory.ui.yourPage.YourPageActivity
import com.example.rxpictory.util.DataBindingActivity
import com.example.rxpictory.util.getToken
import kotlinx.android.synthetic.main.activity_reply.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyActivity: DataBindingActivity<com.example.rxpictory.databinding.ActivityReplyBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_reply

    private val viewModel: ReplyViewModel by lazy {
        ViewModelProviders.of(this).get(ReplyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        reply_rv.adapter = ReplyAdapter(viewModel)
        viewModel.doShowUser.observe(this, Observer { startActivity<YourPageActivity>("userPath" to viewModel.userPath.value) })
        viewModel._id.value = intent.getStringExtra("_id")
        viewModel.loadReplyList()
    }

}