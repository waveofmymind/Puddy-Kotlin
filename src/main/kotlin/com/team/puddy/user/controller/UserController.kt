package com.team.puddy.user.controller

import com.team.puddy.user.application.UserService
import com.team.puddy.user.dto.request.UserRegister
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController(
    private val userService: UserService

) {


    @PostMapping("/join")
    fun registerUser(@RequestBody userRegister: UserRegister) {
        userService.joinUser(userRegister)
    }
}