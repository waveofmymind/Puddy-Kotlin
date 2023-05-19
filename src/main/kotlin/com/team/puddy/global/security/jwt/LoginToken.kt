package com.team.puddy.global.security.jwt

data class LoginToken(
    val accessToken: String,
    val refreshToken: String,
)
