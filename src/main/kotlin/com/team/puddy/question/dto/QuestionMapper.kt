package com.team.puddy.question.dto

import com.team.puddy.question.domain.Question
import com.team.puddy.question.dto.request.RegisterQuestion
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface QuestionMapper {

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(source = "registerQuestion.title", target = "title"),
        Mapping(source = "registerQuestion.content", target = "content"),
        Mapping(source = "registerQuestion.postCategory", target = "postCategory"),
        Mapping(source = "registerQuestion.category", target = "category"),
    )
    fun toEntity(registerQuestion: RegisterQuestion): Question
}