package com.team.puddy.user.service

import com.team.puddy.global.error.DuplicateRegisterException
import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.InvalidPasswordException
import com.team.puddy.global.error.UserNotFoundException
import com.team.puddy.global.security.jwt.JwtProvider
import com.team.puddy.user.application.UserService
import com.team.puddy.user.domain.loginToken
import com.team.puddy.user.domain.user
import com.team.puddy.user.domain.userLogin
import com.team.puddy.user.domain.userRegister
import com.team.puddy.user.domain.UserRepository
import com.team.puddy.user.dto.request.toServiceRegister
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceTest : BehaviorSpec({

    val userRepository = mockk<UserRepository>(relaxed = true)
    val jwtProvider = mockk<JwtProvider>(relaxed = true)
    val passwordEncoder = mockk<PasswordEncoder>(relaxed = true)
    val userService = UserService(userRepository, jwtProvider, passwordEncoder)


    Given("회원가입을 할 때") {
        val userRegister = userRegister()
        every { userRepository.existsByAccount(userRegister.account) } returns false
        every { passwordEncoder.encode(userRegister.password) } returns "encodedPassword"
        every { userRepository.save(any()) } returnsArgument 0

        When("처음 가입한 아이디일 경우") {
            userService.joinUser(userRegister.toServiceRegister())
            Then("회원가입이 된다.") {
                verify { userRepository.existsByAccount(userRegister.account) }
                verify { passwordEncoder.encode(userRegister.password) }
                verify { userRepository.save(any()) }
            }
        }

        When("이미 가입한 아이디일 경우") {
            every { userRepository.existsByAccount(userRegister.account) } returns true
            Then("이미 중복 가입 예외가 발생한다.") {
                val exception = shouldThrow<DuplicateRegisterException> {
                    userService.joinUser(userRegister.toServiceRegister())
                }
                exception.errorCode shouldBe ErrorCode.DUPLICATE_REGISTER
            }
        }
    }

    Given("로그인을 할 때") {
        val loginToken = loginToken()
        val userLogin = userLogin()
        val user = user()

        When("모든 조건이 통과했을 경우") {
            every { userRepository.findByAccount(userLogin.account) } returns user
            every { passwordEncoder.matches(userLogin.password, user.password) } returns true
            every { jwtProvider.createLoginToken(user) } returns loginToken
            userService.loginUser(userLogin.account, userLogin.password)
            Then("토큰을 반환한다.") {
                verify { userRepository.findByAccount(userLogin.account) }
                verify { passwordEncoder.matches(userLogin.password, user.password) }
                verify { jwtProvider.createLoginToken(user) }
            }
        }

        When("아이디로 유저를 찾지 못했을 경우") {
            every { userRepository.findByAccount(userLogin.account) } returns null
            Then("유저를 찾지 못했다는 예외가 발생한다.") {
                val exception = shouldThrow<UserNotFoundException> {
                    userService.loginUser(userLogin.account, userLogin.password)
                }
                exception.errorCode shouldBe ErrorCode.USER_NOT_FOUND
            }
        }

        When("비밀번호가 틀렸을 경우") {
            every { userRepository.findByAccount(userLogin.account) } returns user
            every { passwordEncoder.matches(userLogin.password, user.password) } returns false
            Then("비밀번호가 틀렸다는 예외가 발생한다.") {
                val exception = shouldThrow<InvalidPasswordException> {
                    userService.loginUser(userLogin.account, userLogin.password)
                }
                exception.errorCode shouldBe ErrorCode.INVALID_PASSWORD
            }
        }
    }

})