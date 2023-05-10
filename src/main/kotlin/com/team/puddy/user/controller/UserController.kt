package com.team.puddy.user.controller

import com.team.puddy.global.common.api.Response
import com.team.puddy.global.security.jwt.LoginToken
import com.team.puddy.user.application.UserService
import com.team.puddy.user.dto.request.UserLogin
import com.team.puddy.user.dto.request.UserRegister
import com.team.puddy.user.dto.request.toServiceRegister
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService

) {

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody request: UserRegister) : Response<Any?> {
        userService.joinUser(request.toServiceRegister())

        return Response.ok(HttpStatus.CREATED)
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun loginUser(@RequestBody request: UserLogin): Response<LoginToken> {

        val loginToken  = userService.loginUser(request.account, request.password)

        return Response.ok(loginToken)
    }
}