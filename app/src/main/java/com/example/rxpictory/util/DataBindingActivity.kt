package com.example.rxpictory.util

import android.arch.lifecycle.Lifecycle
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class DataBindingActivity<T: ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    abstract val layoutId: Int

    private val lifecycleOwner = LifecycleOwner()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    fun register(callback: LifecycleCallback) {
        lifecycleOwner.register(callback)
    }

    fun unregister(callback: LifecycleCallback) {
        lifecycleOwner.unregister(callback)
    }

    private fun notifyEvent(event: Lifecycle.Event) {
        lifecycleOwner.notifyEvent(event)
    }
}