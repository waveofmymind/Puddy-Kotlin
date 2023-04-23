package com.team.puddy.global.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.team.puddy.global.security.auth.AuthConstants
import com.team.puddy.user.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class JwtProvider(
    private val redisTemplate: RedisTemplate<String, String>,

    @Value("\${jwt.secret}")
    private val SECRET: String,
) {
    val ACCESS_EXPIRATION_TIME = 120 * 60 * 1000L // 2시간

    val REFRESH_EXPIRATION_TIME = 14 * 60 * 60 * 24 * 1000L // 14일


    fun createLoginToken(user: User): LoginToken {
        val refreshToken: String = createRefreshToken()
        redisTemplate.opsForValue().set(user.account, refreshToken, 14, TimeUnit.DAYS)
        return LoginToken(AuthConstants.TOKEN_PREFIX.value + createAccessToken(user),
            AuthConstants.TOKEN_PREFIX.value + refreshToken)
    }

    fun createAccessToken(user: User): String {
        return JWT.create()
            .withSubject(user.account)
            .withExpiresAt(Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
            .withClaim("id", user.id)
            .withClaim("nickname", user.nickname)
            .withClaim("email", user.email)
            .withClaim("auth", user.role)
            .sign(Algorithm.HMAC256(SECRET))
    }

    private fun createRefreshToken(): String {
        return JWT.create()
            .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(SECRET))
    }

}