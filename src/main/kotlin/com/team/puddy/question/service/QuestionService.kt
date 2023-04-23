package com.team.puddy.question.service

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.exception.NotFoundException
import com.team.puddy.question.domain.QuestionRepository
import com.team.puddy.question.dto.request.QuestionRegister
import com.team.puddy.question.dto.request.toEntity
import com.team.puddy.question.dto.response.QuestionResponse
import com.team.puddy.question.dto.response.toDto
import com.team.puddy.user.infrastructure.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val userRepository : UserRepository,
  ) {

    @Transactional
    fun registerQuestion(questionRegister: QuestionRegister, userId: Long) {
        val findUser = userRepository.findByIdOrNull(userId) ?: throw NotFoundException(ErrorCode.USER_NOT_FOUND)
        val question = questionRegister.toEntity(findUser)
        questionRepository.save(question)
    }

    @Transactional
    fun getQuestion(id: Long) : QuestionResponse {
        val question = questionRepository.findByIdOrNull(id) ?: throw NotFoundException(ErrorCode.QUESTION_NOT_FOUND)

        return toDto(question)


    }


}