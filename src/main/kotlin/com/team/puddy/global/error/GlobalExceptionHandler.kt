package com.team.puddy.global.error

import com.team.puddy.global.common.api.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun businessException(e: BusinessException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }

    @ExceptionHandler(DuplicateRegisterException::class)
    fun duplicateRegisterException(e: DuplicateRegisterException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun notFoundException(e: UserNotFoundException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }

    @ExceptionHandler(InvalidPasswordException::class)
    fun invalidPasswordException(e: InvalidPasswordException): ResponseEntity<Response<Nothing>> {
        return ResponseEntity.status(e.errorCode.httpStatus).body(Response.fail(e.errorCode))
    }
}