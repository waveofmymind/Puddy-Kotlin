package com.team.puddy.global.security

import com.team.puddy.global.security.jwt.JwtAuthenticationEntryPoint
import com.team.puddy.global.security.jwt.JwtAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,

    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
        return authenticationConfiguration.authenticationManager
    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic().disable().formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
            .requestMatchers(HttpMethod.POST, "/users/experts", "/experts/send-docs").hasRole("EXPERT")
            .requestMatchers(HttpMethod.PUT, "/users/experts", "/experts/**").hasRole("EXPERT")
            .requestMatchers(
                HttpMethod.POST,
                "/users/pets",
                "/users/experts",
                "/questions/**",
                "/articles/**",
                "/reviews/**",
                "/users/reissue"
            ).hasAnyRole("USER", "EXPERT")
            .requestMatchers(HttpMethod.PUT, "/questions/**", "/users/**").hasAnyRole("USER", "EXPERT")
            .requestMatchers(HttpMethod.DELETE, "/questions/**", "/articles/**", "/users/**")
            .hasAnyRole("USER", "EXPERT")
            .requestMatchers(HttpMethod.PATCH, "/users/**", "/questions/**", "/articles/**")
            .hasAnyRole("USER", "EXPERT")
            .requestMatchers(HttpMethod.POST, "/users/**", "/oauth/**").permitAll()
            .requestMatchers(
                HttpMethod.GET,
                "/users/**",
                "/reviews/**"
            ).hasAnyRole("USER", "EXPERT")
            .requestMatchers("/users/**", "/experts/**", "/questions/**", "/articles/**", "/home").permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)


        return http.build()
    }
}