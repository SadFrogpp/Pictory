package com.example.rxpictory.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FeedCommentModel(
    @SerializedName("body")
    @Expose
    var body: String,
    @SerializedName("author")
    @Expose
    var author: String,
    @SerializedName("date")
    @Expose
    var date: String
)