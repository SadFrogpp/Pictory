package com.example.rxpictory.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.rxpictory.R
import com.example.rxpictory.databinding.ActivityLoginBinding
import com.example.rxpictory.util.DataBindingActivity

class LoginActivity : DataBindingActivity<ActivityLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_login

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

//        viewModel.goMainEvent.observe(this, Observer { startActivity<>() })
//        viewModel.goRegisterEvent.observe(this, Observer { startActivity<SignUpActivity>() })
    }

}