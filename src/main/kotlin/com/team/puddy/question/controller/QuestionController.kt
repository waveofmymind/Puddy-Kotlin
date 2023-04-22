package com.team.puddy.question.controller

import com.team.puddy.question.dto.request.RegisterQuestion
import com.team.puddy.question.service.QuestionService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
class QuestionController(
    private val questionService: QuestionService

) {
    @PostMapping
    fun registerQuestion(@RequestBody registerQuestion: RegisterQuestion) {
        questionService.registerQuestion(registerQuestion)
    }
}