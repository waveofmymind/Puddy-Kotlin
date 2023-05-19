package com.team.puddy.global.security.auth

enum class AuthConstants(val value : String) {
    HEADER_STRING("Authorization"),
    TOKEN_PREFIX("Bearer ")
}