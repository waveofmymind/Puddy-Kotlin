package com.team.puddy.global.security.auth

import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class JwtUserDetails(
    private val id: Long,
    private val role: String,
    private val isAuthenticated: Boolean,
) : UserDetails {

    constructor(decodedJWT: DecodedJWT) : this(
        id = decodedJWT.getClaim("id").asLong(),
        role = decodedJWT.getClaim("auth").asString(),
        isAuthenticated = true
    )

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return if (isAuthenticated) {
            Collections.singletonList(SimpleGrantedAuthority(role))
        } else Collections.singletonList(SimpleGrantedAuthority(null))
    }


    override fun getPassword(): String? = null

    override fun getUsername(): String? = null

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false

    fun getUserId(): Long = id




}