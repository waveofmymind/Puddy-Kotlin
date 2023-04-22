package com.team.puddy.global.error

import com.team.puddy.global.error.exception.Response
import com.team.puddy.global.error.exception.user.DuplicateRegisterException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRegisterException::class)
    fun duplicateRegisterException(e: DuplicateRegisterException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }
}