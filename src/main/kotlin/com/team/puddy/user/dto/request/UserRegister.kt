package com.team.puddy.user.dto.request

import com.team.puddy.user.domain.User
import kotlin.random.Random

data class UserRegister(
    val account: String,
    val email: String,
    val password: String,
    val username: String,
    val isNotificated: Boolean,
) {
    fun toEntity(): User = User(
            account = this.account,
            email = this.email,
            password = this.password,
            username = this.username,
            nickname = "퍼디" + Random.nextInt(10000, 99999),
            isNotificated = this.isNotificated
        )

}