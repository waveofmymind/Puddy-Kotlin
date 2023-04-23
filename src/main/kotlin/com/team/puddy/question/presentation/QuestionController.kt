package com.team.puddy.question.presentation

import com.team.puddy.global.common.api.Response
import com.team.puddy.global.security.auth.JwtUserDetails
import com.team.puddy.question.dto.request.QuestionRegister
import com.team.puddy.question.dto.response.QuestionResponse
import com.team.puddy.question.service.QuestionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/questions")
class QuestionController(
    private val questionService: QuestionService

) {
    @ResponseStatus(HttpStatus.CREATED)
    fun registerQuestion(@RequestBody questionRegister: QuestionRegister,
                         @AuthenticationPrincipal user : JwtUserDetails) : Response<Nothing>{
        questionService.registerQuestion(questionRegister,user.getUserId())

        return Response.of()
    }

    @GetMapping("/{questionId}")
    fun getQuestion(@PathVariable("questionId") id : Long): Response<QuestionResponse> {

        val question = questionService.getQuestion(id)

        return Response.of(question)
    }
}