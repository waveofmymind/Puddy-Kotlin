package com.team.puddy.global.error.exception

import com.team.puddy.global.error.ErrorCode
import org.springframework.http.HttpStatus

data class Response<T>(
    val httpStatus: HttpStatus,
    val message: String,
    var data: T? = null
) {
    init {
        data = null
    }

    companion object {
        fun of(errorCode: ErrorCode): Response<Nothing> {
            return Response(errorCode.httpStatus, errorCode.message)
        }

        fun <T> of(errorCode: ErrorCode, data: T): Response<T> {
            return Response(errorCode.httpStatus, errorCode.message, data)
        }

        fun fail(errorCode: ErrorCode): Response<Nothing> {
            return Response(errorCode.httpStatus, errorCode.message)
        }
    }
}
