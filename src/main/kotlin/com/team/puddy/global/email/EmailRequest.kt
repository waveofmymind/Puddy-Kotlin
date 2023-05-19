package com.team.puddy.global.email

data class EmailRequest (
    val toAddress : String,
    val title : String,
    val message : String,
    val fromAddress : String,
)