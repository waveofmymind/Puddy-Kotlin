package com.team.puddy.image.domain

import com.team.puddy.global.config.jpa.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Image(

    val savedName: String,

    val originalName: String,

    val imagePath: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseEntity()
