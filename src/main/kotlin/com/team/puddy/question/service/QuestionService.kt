package com.team.puddy.question.service

import com.team.puddy.question.domain.QuestionRepository
import com.team.puddy.question.dto.request.RegisterQuestion
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class QuestionService(
    private val questionRepository: QuestionRepository
) {

    fun registerQuestion(registerQuestion: RegisterQuestion): Unit {



    }
}