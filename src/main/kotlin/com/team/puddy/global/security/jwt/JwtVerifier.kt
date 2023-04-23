package com.team.puddy.global.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.exception.BusinessException
import com.team.puddy.global.error.exception.NotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component


@Component
class JwtVerifier(
    private val redisTemplate: RedisTemplate<String, String>,

    @Value("\${jwt.secret}")
    val SECRET: String,
) {
    fun verify(token: String): DecodedJWT {
        return try {
            JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
        } catch (e: Exception) {
            throw BusinessException(ErrorCode.TOKEN_VERIFY_FAIL)
        }
    }

    fun verifyRefreshToken(account: String, refreshToken: String, redisTemplate: RedisTemplate<String, String>) {
        val findRefreshToken = redisTemplate.opsForValue().get(account)

        findRefreshToken?.let { findToken ->
            if (findToken != refreshToken) {
                throw NotFoundException(ErrorCode.TOKEN_VERIFY_FAIL)
            }
        } ?: throw NotFoundException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)
    }

    fun expireRefreshToken(account: String) {
        redisTemplate.delete(account)
    }

    fun parseAccount(accessToken: String): String {
        return JWT.decode(accessToken).subject
    }


}