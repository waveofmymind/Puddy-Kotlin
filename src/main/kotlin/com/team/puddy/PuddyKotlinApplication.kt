package com.team.puddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PuddyKotlinApplication

fun main(args: Array<String>) {
    runApplication<PuddyKotlinApplication>(*args)
}
