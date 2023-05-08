package com.team.puddy.user.dto.request

import com.team.puddy.user.domain.User
import com.team.puddy.user.domain.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.random.Random

data class UserRegister(
    val account: String,
    val email: String,
    val password: String,
    val username: String,
    val isNotificated: Boolean,
)

data class UserServiceRegister(
    val account: String,
    val email: String,
    val password: String,
    val username: String,
    val isNotificated: Boolean,
)

fun UserRegister.toServiceRegister(): UserServiceRegister = UserServiceRegister(
    account = account,
    email = email,
    password = password,
    username = username,
    isNotificated = isNotificated,
)

fun UserServiceRegister.toEntity(password: String): User = User(
    account = this.account,
    email = this.email,
    password = password,
    username = this.username,

    nickname = "퍼디" + Random.nextInt(10000, 99999),
    role = UserRole.USER.role,
    isNotificated = this.isNotificated
)
