package com.team.puddy.question.domain

import com.team.puddy.question.dto.request.QuestionRegister
import com.team.puddy.user.domain.user

fun question() = Question(
    id = 1L,
    title = "질문입니다",
    content = "질문입니다",
    postCategory = 1,
    isSolved = false,
    user = user(),
    category = Category.건강,
)

fun questionRegister() = QuestionRegister(
    "Test title",
    "Test content",
    1,
    "산책"
)