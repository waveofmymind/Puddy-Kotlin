package com.team.puddy.question.dto.response

import com.team.puddy.question.domain.Question
import com.team.puddy.user.domain.User
import java.time.LocalDateTime

data class QuestionResponse(
    val id : Long,
    val title : String,
    val content : String,
    val postCategory : Int,
    val category : String,
    val viewCount : Int,
    val user : User,
    val createdAt : LocalDateTime,
    )

fun toDto(question: Question) = QuestionResponse(
    id = question.id!!,
    title = question.title,
    content = question.content,
    postCategory = question.postCategory,
    category = question.category.name,
    viewCount = question.viewCount,
    user = question.user,
    createdAt = question.createdAt!!,

    )