package com.example.rxpictory.model

import com.google.gson.annotations.SerializedName

data class Posts(
    var liker: List<String>,
    var imageName: String,
    var imagePath: String,
    var comments: List<CommentModel>,
    @SerializedName("_id")
    var postId: String,
    var text: String,
    var username: String,
    var id: String,
    var __v: Int,
    var postCode: Int,
    var profilePath: String
)