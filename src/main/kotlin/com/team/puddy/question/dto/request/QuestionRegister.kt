package com.team.puddy.question.dto.request

import com.team.puddy.question.domain.Category
import com.team.puddy.question.domain.Question
import com.team.puddy.user.domain.User

data class QuestionRegister(

    val title : String,
    val content : String,
    val postCategory : Int,
    val category : String,

) {
}

fun QuestionRegister.toEntity(user : User) = Question(
    title = title,
    content = content,
    postCategory = postCategory,
    isSolved = false,
    user = user,
    category = Category.valueOf(category),
)