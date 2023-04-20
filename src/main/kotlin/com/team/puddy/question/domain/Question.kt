package com.team.puddy.question.domain

import com.team.puddy.global.config.jpa.BaseEntity
import com.team.puddy.user.domain.User
import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor

@Entity
@Table(name = "question")
class Question(

    var title: String,
    var content: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User = User(),

    var viewCount : Int = 0,

    var isSolved : Boolean = false,

    @Enumerated(EnumType.STRING)
    var category : Category,

    @Column(name = "post_category")
    val postCategory: Int,

    ) : BaseEntity() {

}