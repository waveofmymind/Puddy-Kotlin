package com.team.puddy.question.dto

import com.team.puddy.image.domain.Image
import com.team.puddy.question.domain.Category
import com.team.puddy.question.domain.Question
import com.team.puddy.user.domain.User
import org.springframework.web.multipart.MultipartFile

data class QuestionRegister(
    val title: String,
    val content: String,
    val postCategory: Int,
    val category: String,
)

data class QuestionServiceRegister(
    val title: String,
    val content: String,
    val postCategory: Int,
    val category: String,
    val images: List<MultipartFile>?,
)

fun QuestionRegister.toServiceRegister(images : List<MultipartFile>) = QuestionServiceRegister(
    title = title,
    content = content,
    postCategory = postCategory,
    category = category,
    images = images,
)

fun QuestionServiceRegister.toEntity(user: User,imageList : List<Image>?) = Question(
    title = title,
    content = content,
    postCategory = postCategory,
    isSolved = false,
    user = user,
    imageList = imageList?.toMutableList() ?: mutableListOf(),
    category = Category.valueOf(category),
)

data class QuestionModify(
    val title: String,
    val content: String,
    val postCategory: Int,
    val category: String,
)
//TODO: 수정 엔티티 이미지 추가