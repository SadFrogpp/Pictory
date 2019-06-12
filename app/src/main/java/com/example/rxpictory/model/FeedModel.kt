package com.example.rxpictory.model

import com.example.rxpictory.model.FeedCommentModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FeedModel(
    @SerializedName("username")
    @Expose
    var username: String,
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("liker_id")
    @Expose
    var likerId: List<String>,
    @SerializedName("imageName")
    @Expose
    var imageName: String,
    @SerializedName("comments")
    @Expose
    var comments: List<FeedCommentModel>,
    var imagePath:String,
    var text:String?
)