package com.team.puddy.question.presentation

import com.team.puddy.global.common.api.Response
import com.team.puddy.global.security.auth.JwtUserDetails
import com.team.puddy.question.application.QuestionService
import com.team.puddy.question.dto.QuestionRegister
import com.team.puddy.question.dto.QuestionResponse
import com.team.puddy.question.dto.toServiceRegister
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/questions")
class QuestionController(
    private val questionService: QuestionService

) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerQuestion(@RequestBody request: QuestionRegister,
                         @AuthenticationPrincipal user : JwtUserDetails) : Response<Any?>{
        questionService.registerQuestion(request.toServiceRegister(),user.getUserId())

        return Response.of(HttpStatus.CREATED)
    }

    @GetMapping("/{questionId}")
    fun getQuestion(@PathVariable("questionId") id : Long): Response<QuestionResponse?> {

        val question = questionService.getQuestion(id)

        return Response.ok(question)
    }

}