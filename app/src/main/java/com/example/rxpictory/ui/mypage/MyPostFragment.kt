package com.example.rxpictory.ui.mypage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rxpictory.R
import com.example.rxpictory.adapter.MyPostAdapter
import com.example.rxpictory.ui.content.ContentActivity
import com.example.rxpictory.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_my_post.*
import org.jetbrains.anko.support.v4.startActivity

class MyPostFragment : DataBindingFragment<com.example.rxpictory.databinding.FragmentMyPostBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_my_post


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(MyPageViewModel::class.java)
        binding.vm = viewModel
        myPost_rv.adapter = MyPostAdapter(viewModel)
        viewModel.getMypage()
        viewModel.doShowContent.observe(this, Observer { startActivity<ContentActivity>("_id" to viewModel._id.value)})
    }


}
