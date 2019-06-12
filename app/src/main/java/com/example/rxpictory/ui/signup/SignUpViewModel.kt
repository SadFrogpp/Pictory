package com.example.rxpictory.ui.signup

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.example.rxpictory.connect.Connecter
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(val signUpNavigator: SignUpConstract) : ViewModel() {
    val registerImage = MutableLiveData<CircleImageView>()
    val registerName = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerBirth = MutableLiveData<String>()

    fun doSignUp(view: View) {
        if (registerName.isValueBlank() || registerId.isValueBlank()
            || registerPw.isValueBlank()|| registerBirth.isValueBlank()) {
            Toast.makeText(view.context,"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val json = JsonObject().apply {
                //addProperty("profilePhoto", registerImage)
                addProperty("username", registerName.value)
                addProperty("id", registerId.value)
                addProperty("pw", registerPw.value)
                addProperty("birth", registerBirth.value)
            }
            Connecter.api.signUp(json).enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("회원가입", "성공")
                    Log.d("비밀번호", registerPw.value)
                    Toast.makeText(view.context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    signUpNavigator.intentToLogin()
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    Log.d("회원가입", "실패")
                    Toast.makeText(view.context, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    signUpNavigator.intentToLogin()
                }
            })
        }
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

    fun addProfileImage() = signUpNavigator.addProfileImage()

}
