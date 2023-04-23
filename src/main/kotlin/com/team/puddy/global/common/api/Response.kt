package com.team.puddy.global.common.api

import com.team.puddy.global.error.ErrorCode
import org.springframework.http.HttpStatus

data class Response<T>(
    val httpStatus: HttpStatus,
    val message: String,
    var data: T? = null
) {

    companion object {

        @JvmStatic fun <T> of(data : T) : Response<T> {
            return Response(HttpStatus.OK, "성공", data)
        }

        @JvmStatic fun of() : Response<Nothing> {
            return Response(HttpStatus.OK, "성공", null)
        }

        @JvmStatic fun fail(errorCode: ErrorCode): Response<Nothing> {
            return Response(errorCode.httpStatus, errorCode.message)
        }
    }
}
