package com.team.puddy.question.dto.request

data class RegisterQuestion(

    val title : String,
    val content : String,
    val postCategory : Int,
    val category : String,

) {
}