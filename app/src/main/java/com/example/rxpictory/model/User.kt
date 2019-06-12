package com.example.rxpictory.model

data class User(
    var following: List<String>,
    var follower: List<String>,
    var likedPost: List<String>,
    var imageName: List<String>,
    var imagePath: List<String>,
    var profilePath: String,
    var username: String,
    var id: String,
    var pw: String,
    var birth: String,
    var active: Boolean,
    var profileIMG: String
)