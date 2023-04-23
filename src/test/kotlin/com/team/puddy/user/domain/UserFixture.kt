package com.team.puddy.user.domain

import com.team.puddy.global.security.jwt.LoginToken
import com.team.puddy.user.dto.request.UserLogin
import com.team.puddy.user.dto.request.UserRegister

fun user(): User = User(
    id = 1L,
    account = "puddy",
    nickname = "퍼디1234",
    username = "퍼디",
    email = "test@example.com",
    password = "test123",
    role = "ROLE_USER",
    isNotificated = true
)

fun userRegister(): UserRegister = UserRegister(
    account = "puddy",
    email = "puddy@gmail.com",
    password = "1234",
    username = "퍼디",
    isNotificated = true
)

fun userLogin(): UserLogin = UserLogin(
    account = "puddy",
    password = "1234"
)

fun loginToken() = LoginToken(
    accessToken = "sample-access-token",
    refreshToken = "sample-refresh-token"
)