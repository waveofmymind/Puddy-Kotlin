package com.team.puddy.global.error

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

enum class ErrorCode(val httpStatus: HttpStatus, val message: String) {

    //USER
    DUPLICATE_REGISTER(HttpStatus.CONFLICT, "이미 가입된 유저입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.CONFLICT, "비밀번호가 일치하지 않습니다."),

    //QUESTION
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 문제입니다."),

    //TOKEN
    TOKEN_VERIFY_FAIL(HttpStatus.UNAUTHORIZED, "토큰 검증에 실패하였습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "리프레시 토큰을 찾을 수 없습니다."),


}