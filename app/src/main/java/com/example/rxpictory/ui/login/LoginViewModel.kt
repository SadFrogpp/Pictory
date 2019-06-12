package com.example.rxpictory.ui.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.example.rxpictory.connect.Connecter
import com.example.rxpictory.database.Auth
import com.example.rxpictory.database.AuthDatabase
import com.example.rxpictory.model.LoginModel
import com.example.rxpictory.util.SingleLiveEvent
import com.example.rxpictory.util.saveToken
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val app: Application) : AndroidViewModel(app) {
    val loginId = MutableLiveData<String>()
    val loginPw = MutableLiveData<String>()

    val goMainEvent = SingleLiveEvent<Any>()
    val goRegisterEvent = SingleLiveEvent<Any>()

    fun doLogin(view: View) {
        if (loginId.isValueBlank()) {
            Toast.makeText(view.context, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else if(loginPw.isValueBlank()) {
            Toast.makeText(view.context, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else {
            val auth = Auth(loginId.value!!, loginPw.value!!)
            val json = JsonObject().apply {
                addProperty("id", loginId.value)
                addProperty("pw", loginPw.value)
            }
            Connecter.api.login(json).enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    doAsync {
                        AuthDatabase.getInstance(view.context)!!
                            .getAuthDao().insert(auth)
                    }
                    saveToken(view.context, response.body()!!.token)
                    toMain()
                }

                override fun onFailure(call: Call<LoginModel>?, t: Throwable?) {
                    Toast.makeText(view.context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun toSignUpBtn() = goRegisterEvent.call()
    fun toMain() = goMainEvent.call()

    fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
}