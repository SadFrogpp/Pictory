package com.example.rxpictory.ui.main.feed

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.example.rxpictory.R
import com.example.rxpictory.adapter.FeedAdapter
import com.example.rxpictory.databinding.FragmentFeedBinding
import com.example.rxpictory.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment: DataBindingFragment<FragmentFeedBinding>() {


override val layoutId: Int
    get() = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(FeedViewModel::class.java)
        binding.viewModel = viewModel
        feed_recyclerview.adapter= FeedAdapter(viewModel)
        viewModel.getFeed()
    }

//    fun setAdapterData() {
//        var feedData = ArrayList<FeedModel>()
//        api.getFeed().enqueue(object: Callback<ArrayList<FeedModel>>{
//            override fun onResponse(call: Call<ArrayList<FeedModel>>?, response: Response<ArrayList<FeedModel>>?) {
//                feedData = response!!.body()!!
//            }
//
//            override fun onFailure(call: Call<ArrayList<FeedModel>>?, t: Throwable?) {
//                toast("피드를 가져올 수 없습니다.")
//            }
//        })
//        return feedData
//    }


}