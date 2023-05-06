package com.team.puddy.question.application

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.QuestionNotFoundException
import com.team.puddy.global.error.UserNotFoundException
import com.team.puddy.question.domain.QuestionRepository
import com.team.puddy.question.dto.QuestionRegister
import com.team.puddy.question.dto.QuestionResponse
import com.team.puddy.question.dto.toDto
import com.team.puddy.question.dto.toEntity
import com.team.puddy.user.infrastructure.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
) {

    @Transactional
    fun registerQuestion(questionRegister: QuestionRegister, userId: Long) {
        userRepository.findByIdOrNull(userId)?.also { findUser ->
            questionRegister.toEntity(findUser).also { question ->
                questionRepository.save(question)
            }
        } ?: throw UserNotFoundException(ErrorCode.USER_NOT_FOUND)
    }

    @Transactional(readOnly = true)
    fun getQuestion(id: Long): QuestionResponse {
        return run {
            val question = questionRepository.findByIdOrNull(id)
                ?: throw QuestionNotFoundException(ErrorCode.QUESTION_NOT_FOUND)
            toDto(question)
        }
    }
}