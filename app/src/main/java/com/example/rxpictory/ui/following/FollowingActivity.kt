package com.example.rxpictory.ui.following

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rxpictory.R
import com.example.rxpictory.adapter.FollowingAdapter
import com.example.rxpictory.databinding.ActivityFollowingBinding
import com.example.rxpictory.ui.yourPage.YourPageActivity
import com.example.rxpictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_following.*
import org.jetbrains.anko.startActivity

class FollowingActivity : DataBindingActivity<ActivityFollowingBinding>(){

    override val layoutId: Int
        get() = R.layout.activity_following

    val viewModel: FollowingViewModel by lazy {
        ViewModelProviders.of(this).get(FollowingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        following_list_rv.adapter = FollowingAdapter(viewModel)
        viewModel.userID.value = intent.getStringExtra("userID")
        viewModel.setAdapterData()
        viewModel.doShowUser.observe(this, Observer { startActivity<YourPageActivity>("userID" to viewModel.userID) })
    }
}
