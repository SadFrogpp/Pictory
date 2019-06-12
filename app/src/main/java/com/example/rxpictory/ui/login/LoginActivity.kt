package com.example.rxpictory.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.rxpictory.R
import com.example.rxpictory.databinding.ActivityLoginBinding
import com.example.rxpictory.ui.main.MainActivity
import com.example.rxpictory.ui.signup.SignUpActivity
import com.example.rxpictory.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class LoginActivity : DataBindingActivity<ActivityLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_login

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.goMainEvent.observe(this, Observer {
            startActivity<MainActivity>()
            finish()
        })
        viewModel.goRegisterEvent.observe(this, Observer { startActivity<SignUpActivity>() })
    }

}