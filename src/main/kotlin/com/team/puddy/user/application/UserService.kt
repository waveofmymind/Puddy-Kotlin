package com.team.puddy.user.application

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.DuplicateRegisterException
import com.team.puddy.global.error.InvalidPasswordException
import com.team.puddy.global.error.UserNotFoundException
import com.team.puddy.global.security.jwt.JwtProvider
import com.team.puddy.global.security.jwt.LoginToken
import com.team.puddy.user.dto.request.toEntity
import com.team.puddy.user.domain.UserRepository
import com.team.puddy.user.dto.request.UserLogin
import com.team.puddy.user.dto.request.UserServiceRegister
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder,
) {

    fun joinUser(request: UserServiceRegister) {
        if (userRepository.existsByAccount(request.account)) {
            throw DuplicateRegisterException(ErrorCode.DUPLICATE_REGISTER)
        }
        val encodedPassword = passwordEncoder.encode(request.password)
        val user = request.toEntity(encodedPassword)
        userRepository.save(user)
    }

    fun loginUser(account : String, password : String) : LoginToken {
        val findUser = userRepository.findByAccount(account) ?: throw UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        if (!passwordEncoder.matches(password,findUser.password)) {
            throw InvalidPasswordException(ErrorCode.INVALID_PASSWORD)
        }

        return jwtProvider.createLoginToken(findUser)
    }




}