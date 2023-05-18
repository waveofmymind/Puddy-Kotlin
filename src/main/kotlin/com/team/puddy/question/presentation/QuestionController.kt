package com.team.puddy.question.presentation

import com.team.puddy.global.common.api.Response
import com.team.puddy.global.security.auth.JwtUserDetails
import com.team.puddy.question.application.QuestionService
import com.team.puddy.question.dto.QuestionRegister
import com.team.puddy.question.dto.QuestionResponse
import com.team.puddy.question.dto.toServiceRegister
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/questions")
class QuestionController(
    private val questionService: QuestionService

) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun registerQuestion(@RequestPart("request") request: QuestionRegister,
                         @RequestPart(value = "images", required = false) images: List<MultipartFile>,
                         @AuthenticationPrincipal user : JwtUserDetails) : Response<Any?>{
        questionService.registerQuestion(request.toServiceRegister(images),user.getUserId())

        return Response.of(HttpStatus.CREATED, null)
    }

    @GetMapping("/{questionId}")
    fun getQuestion(@PathVariable("questionId") id : Long): Response<QuestionResponse> {

        val question = questionService.getQuestion(id)

        return Response.ok(question)
    }

    @GetMapping
    fun getQuestionList(@RequestParam(value = "keyword",defaultValue = "") searchKeyword : String,
                        @RequestParam(value = "sort", defaultValue = "desc") sort: String,
                        @RequestParam page: Int) : Response<Any?> {
        val page = PageRequest.of(page - 1, 10)
        questionService.getQuestionList(page, searchKeyword, sort)
        return Response.ok(null)
    }

}