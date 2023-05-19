package com.team.puddy.global.slack

import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SlackService {

    fun sendSlackMessage(username : String) {
        val message = """
            $username 님이 문서를 보냈습니다! 확인해주세요!
        """.trimIndent()
        val url = "https://hooks.slack.com/services/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"
        SlackApi(url).call(SlackMessage(message))
    }
}