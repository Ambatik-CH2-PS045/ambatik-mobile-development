package com.example.ambatik.data.pref

data class UserModel(
    val username: String,
    val token: String,
    val isLogin: Boolean = false,
    val id: Int
)