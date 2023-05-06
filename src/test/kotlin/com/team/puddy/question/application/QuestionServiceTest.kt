package com.team.puddy.question.application

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.UserNotFoundException
import com.team.puddy.question.domain.QuestionRepository
import com.team.puddy.question.domain.questionRegister
import com.team.puddy.user.domain.user
import com.team.puddy.user.infrastructure.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull


class QuestionServiceTest : BehaviorSpec({

    val questionRepository = mockk<QuestionRepository>(relaxed = true)
    val userRepository = mockk<UserRepository>(relaxed = true)
    val questionService = QuestionService(questionRepository, userRepository)

    Given("질문글을 등록할 때") {
        val userId = 1L
        val questionRegister = questionRegister()
        val user = user()

        every { userRepository.findByIdOrNull(userId) } returns user
        every { questionRepository.save(any()) } returnsArgument 0

        When("모든 정보가 있으면") {
            questionService.registerQuestion(questionRegister, userId)

            Then("질문글이 등록된다.") {
                verify { questionRepository.save(any()) }
            }
        }

        When("없는 유저일 경우") {
            every { userRepository.findByIdOrNull(userId) } throws UserNotFoundException(ErrorCode.USER_NOT_FOUND)

            Then("Not Found 예외가 발생한다.") {
                val exception = shouldThrow<UserNotFoundException> {
                    questionService.registerQuestion(questionRegister, userId)
                }
                exception.errorCode shouldBe ErrorCode.USER_NOT_FOUND
            }
        }
    }




})