package com.example.rxpictory.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    val posts: ArrayList<Posts>,
    val user: User
)

