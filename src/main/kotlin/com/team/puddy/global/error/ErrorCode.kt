package com.team.puddy.global.error

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

enum class ErrorCode(val httpStatus: HttpStatus, val message: String) {

    DUPLICATE_REGISTER(HttpStatus.CONFLICT, "이미 가입된 유저입니다.")


}