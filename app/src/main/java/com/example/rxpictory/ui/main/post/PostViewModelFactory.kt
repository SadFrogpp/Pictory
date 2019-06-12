package com.example.rxpictory.ui.main.post

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class PostViewModelFactory(val constract: PostConstract?, val app: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
        = modelClass.getConstructor(PostFragment::class.java).newInstance(constract, app)
}