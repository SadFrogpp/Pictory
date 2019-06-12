package com.example.rxpictory.ui.follower

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.rxpictory.R
import com.example.rxpictory.adapter.FollowerAdapter
import com.example.rxpictory.connect.Connecter.api
import com.example.rxpictory.databinding.ActivityFollwerBinding
import com.example.rxpictory.model.FollowerModel
import com.example.rxpictory.ui.yourPage.YourPageActivity
import com.example.rxpictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_follwer.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerActivity: DataBindingActivity<ActivityFollwerBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_follwer

    private val viewModel: FollowerViewModel by lazy {
        ViewModelProviders.of(this).get(FollowerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel
        follower_list_rv.adapter = FollowerAdapter(viewModel)
        viewModel.userID.value = intent.getStringExtra("id")
        viewModel.setAdapterData()
        viewModel.doShowUser.observe(this, Observer { startActivity<YourPageActivity>("userID" to viewModel.userID) })
    }

}