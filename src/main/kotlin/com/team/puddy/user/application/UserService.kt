package com.team.puddy.user.application

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.exception.user.DuplicateRegisterException
import com.team.puddy.user.dto.request.UserRegister
import com.team.puddy.user.infrastructure.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository,
) {

    fun joinUser(userRegister: UserRegister) {
        if (userRepository.existsByAccount(userRegister.account)) {
            throw DuplicateRegisterException(ErrorCode.DUPLICATE_REGISTER)
        }

        val user = userRegister.toEntity()
        userRepository.save(user)
    }


}