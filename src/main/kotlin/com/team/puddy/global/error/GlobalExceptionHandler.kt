package com.team.puddy.global.error

import com.team.puddy.global.error.exception.user.DuplicateRegisterException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRegisterException::class)
    fun duplicateRegisterException(e: DuplicateRegisterException): ResponseEntity<String> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(e.errorCode.message)
    }
}