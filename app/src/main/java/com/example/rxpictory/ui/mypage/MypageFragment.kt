package com.example.rxpictory.ui.mypage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.example.rxpictory.R
import com.example.rxpictory.databinding.FragmentMypageBinding
import com.example.rxpictory.ui.FollowerActivity
import com.example.rxpictory.ui.ProfileEditActivity
import com.example.rxpictory.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity

class MypageFragment : DataBindingFragment<FragmentMypageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_mypage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(MyPageViewModel::class.java)
        binding.vm = viewModel
        register(binding.vm!!)
        see_follower.setOnClickListener {
            startActivity<FollowerActivity>()
        }

        viewModel.doEditEvent.observe(this, Observer { startActivity<ProfileEditActivity>() })

        fragmentManager?.beginTransaction().run {
            this!!.replace(R.id.myPageFrame, MyPostFragment())
                commit()
        }

        myPageNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        edit_profile_btn.setOnClickListener(View.OnClickListener {
            Log.d("hello", "Hello")
        })
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = fragmentManager?.beginTransaction()
        when (item.itemId) {
            R.id.mypost -> {
                transaction?.replace(R.id.myPageFrame, MyPostFragment())
                transaction?.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.like -> {
                transaction?.replace(R.id.myPageFrame, MyLikeFragment())
                transaction?.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.mypost -> {
                fragmentManager?.beginTransaction()?.replace(R.id.myPageFrame, MyPostFragment())?.commit()
            }
            R.id.like -> {
                fragmentManager?.beginTransaction()?.replace(R.id.myPageFrame, MyLikeFragment())?.commit()
            }
        }
        return super.onContextItemSelected(item)
    }

    fun goToEditProfile() {
        startActivity<ProfileEditActivity>()
    }

}