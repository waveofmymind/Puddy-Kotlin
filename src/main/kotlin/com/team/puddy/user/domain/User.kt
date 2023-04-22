package com.team.puddy.user.domain

import com.team.puddy.global.config.jpa.BaseEntity
import com.team.puddy.question.domain.Question
import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor

@Entity
@Table(name = "\"user\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "account", nullable = false, unique = true)
    val account: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    var password: String,

    var nickname: String,

    val username: String,

    var isNotificated: Boolean = false,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var questionList: List<Question> = listOf()
) : BaseEntity() {

}
