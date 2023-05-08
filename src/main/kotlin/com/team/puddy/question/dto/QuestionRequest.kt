package com.team.puddy.question.dto

import com.team.puddy.question.domain.Category
import com.team.puddy.question.domain.Question
import com.team.puddy.user.domain.User

data class QuestionRegister(
    val title : String,
    val content : String,
    val postCategory : Int,
    val category : String, )

data class QuestionServiceRegister(
    val title : String,
    val content : String,
    val postCategory : Int,
    val category : String,
)

fun QuestionRegister.toServiceRegister() = QuestionServiceRegister(
    title = title,
    content = content,
    postCategory = postCategory,
    category = category,
)

fun QuestionServiceRegister.toEntity(user : User) = Question(
    title = title,
    content = content,
    postCategory = postCategory,
    isSolved = false,
    user = user,
    category = Category.valueOf(category),
)

data class QuestionModify(
    val title : String,
    val content : String,
    val postCategory : Int,
    val category : String, )
//TODO: 이미지 추가