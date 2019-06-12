package com.example.rxpictory.ui.signup

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import com.example.rxpictory.R
import com.example.rxpictory.databinding.ActivitySignupBinding
import com.example.rxpictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity: DataBindingActivity<ActivitySignupBinding>(), SignUpConstract {

    override val layoutId: Int
        get() = R.layout.activity_signup

    private val factory = SignUpViewModelFactory(this)
    private val viewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        binding.setLifecycleOwner(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === Activity.RESULT_OK) {
                try {
                    val inGallery = contentResolver.openInputStream(data!!.data)
                    val img = BitmapFactory.decodeStream(inGallery)
                    inGallery!!.close()
                    profileImage.setImageBitmap(img)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun successSignUp() {
        // 토큰 저장

    }

    fun failSignUp(){
        // 토큰
    }

    override fun intentToLogin() {
        finish()
    }

    override fun addProfileImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }
}