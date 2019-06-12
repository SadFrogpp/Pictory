package com.example.rxpictory.ui.signup

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class SignUpViewModelFactory(private val signUpNavigator: SignUpConstract) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SignUpConstract::class.java).newInstance(signUpNavigator)
    }
}