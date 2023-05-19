package com.team.puddy.question.application

import com.team.puddy.global.error.ErrorCode
import com.team.puddy.global.error.QuestionNotFoundException
import com.team.puddy.global.error.UserNotFoundException
import com.team.puddy.image.domain.Image
import com.team.puddy.image.service.ImageService
import com.team.puddy.question.domain.QuestionRepository
import com.team.puddy.question.dto.*
import com.team.puddy.user.domain.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
    private val imageService: ImageService,
) {

    @Transactional
    fun registerQuestion(request: QuestionServiceRegister, userId: Long) {

        val imageList = imageService.uploadImageList(request.images)

        userRepository.findByIdOrNull(userId)?.also { findUser ->
            request.toEntity(findUser,imageList).also { question ->
                questionRepository.save(question)

            }
        } ?: throw UserNotFoundException(ErrorCode.USER_NOT_FOUND)
    }

    fun getQuestion(id: Long): QuestionResponse {
        return run {
            val question = questionRepository.findByIdOrNull(id)
                ?: throw QuestionNotFoundException(ErrorCode.QUESTION_NOT_FOUND)
            toDto(question)
        }
    }

    fun getQuestionList(page: PageRequest, searchKeyword: String, sort: String) {
        questionRepository.findAll(page)
    }
}
