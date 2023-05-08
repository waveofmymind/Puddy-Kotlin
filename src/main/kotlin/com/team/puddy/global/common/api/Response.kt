package com.team.puddy.global.common.api

import com.team.puddy.global.error.ErrorCode
import org.springframework.http.HttpStatus

data class Response<T>(
    val code : Int,
    val status: HttpStatus,
    val message: String,
    var data: T? = null
) {

    companion object {

        @JvmStatic fun <T> of(httpStatus: HttpStatus, message: String, data: T): Response<T> {
            return Response(httpStatus.value(), httpStatus, message, data)
        }

       @JvmStatic fun <T> of(httpStatus: HttpStatus, data: T): Response<T> {
            return of(httpStatus, httpStatus.name, data)
        }

       @JvmStatic fun <T> ok(data: T): Response<T> {
            return of(HttpStatus.OK, data)
        }

    }
}
