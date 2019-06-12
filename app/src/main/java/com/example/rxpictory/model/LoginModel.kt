package com.example.rxpictory.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("token")
    var token: String
)