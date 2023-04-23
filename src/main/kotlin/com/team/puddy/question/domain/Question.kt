package com.team.puddy.question.domain

import com.team.puddy.answer.domain.Answer
import com.team.puddy.global.config.jpa.BaseEntity
import com.team.puddy.user.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "question")
class Question(

    var title: String,
    var content: String,

    var viewCount: Int = 0,

    var isSolved: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    var category: Category,

    @Column(name = "post_category")
    val postCategory: Int,


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    ) : BaseEntity() {

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var answerList: MutableList<Answer> = mutableListOf()

}