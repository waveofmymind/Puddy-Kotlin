package com.team.puddy.user.infrastructure

import com.team.puddy.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByAccount(account : String) : Boolean
}