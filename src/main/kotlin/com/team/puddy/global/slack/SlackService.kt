package com.team.puddy.global.slack

import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SlackService(
    @Value("\${slack.webhook.url}")
    private val url: String

) {

    fun sendSlackMessage(username : String) {
        val message = """
            $username 님이 문서를 보냈습니다! 확인해주세요!
        """.trimIndent()
        SlackApi(url).call(SlackMessage(message))
    }
}