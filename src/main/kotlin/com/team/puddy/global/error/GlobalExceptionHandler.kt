package com.team.puddy.global.error

import com.team.puddy.global.common.api.Response
import com.team.puddy.global.error.exception.NotFoundException
import com.team.puddy.global.error.exception.user.DuplicateRegisterException
import com.team.puddy.global.error.exception.user.InvalidPasswordException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRegisterException::class)
    fun duplicateRegisterException(e: DuplicateRegisterException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(e: NotFoundException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }

    @ExceptionHandler(InvalidPasswordException::class)
    fun invalidPasswordException(e: InvalidPasswordException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }
}