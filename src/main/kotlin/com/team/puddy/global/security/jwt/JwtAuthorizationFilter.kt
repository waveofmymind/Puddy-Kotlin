package com.team.puddy.global.security.jwt

import com.auth0.jwt.interfaces.DecodedJWT
import com.team.puddy.global.security.auth.AuthConstants
import com.team.puddy.global.security.auth.JwtUserDetails
import com.team.puddy.global.error.BusinessException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import java.io.IOException


@Component
class JwtAuthorizationFilter(
    private val jwtVerifier: JwtVerifier,
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader(AuthConstants.HEADER_STRING.value)

        if (authorizationHeader == null || !authorizationHeader.startsWith(AuthConstants.TOKEN_PREFIX.value)) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.replace(AuthConstants.TOKEN_PREFIX.value, "")
        try {
            val context: SecurityContext = SecurityContextHolder.createEmptyContext()
            val decodedJWT: DecodedJWT = jwtVerifier.verify(token)
            val jwtUserDetails = JwtUserDetails(decodedJWT)
            val authentication = UsernamePasswordAuthenticationToken(
                jwtUserDetails, null, jwtUserDetails.authorities
            )
            context.authentication = authentication
            SecurityContextHolder.setContext(context)
        } catch (e: SecurityException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: MalformedJwtException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: UnsupportedJwtException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: IllegalArgumentException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: ExpiredJwtException) {
            setJwtException(request, JwtException.EXPIRED_TOKEN)
        } catch (e: BusinessException) {
            setJwtException(request, JwtException.EXPIRED_TOKEN)
        } catch (e: Exception) {
            setJwtException(request, JwtException.UNKNOWN_ERROR)
        }
        filterChain.doFilter(request, response)
    }

    private fun setJwtException(request: HttpServletRequest, exception: JwtException) {
        request.setAttribute("exception", exception.name)
    }
}