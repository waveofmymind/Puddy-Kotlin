package com.team.puddy.global.email

import com.team.puddy.global.error.BusinessException
import com.team.puddy.global.error.EmailSendException
import jakarta.mail.MessagingException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {


    private val title = "퍼디 임시 비밀번호 안내 메일입니다."
    private val message = """
            안녕하세요. 퍼디 임시 비밀번호 안내 메일입니다. 
            회원님의 임시 비밀번호는 아래와 같습니다.  로그인 후 반드시 비밀번호를 변경해주세요.
            
            """.trimIndent()
    private val fromAddress = "puddyofficial1@gmail.com"

    fun sendDocs(file: MultipartFile) {
        val message = javaMailSender.createMimeMessage()

        try {
            val helper = MimeMessageHelper(message, true, "UTF-8")
            helper.setFrom(fromAddress)
            helper.setTo("puddyofficial1@gmail.com")
            helper.setSubject("퍼디 문서 전송 메일입니다.")
            helper.setText("퍼디 문서 전송 메일입니다.")
            helper.addAttachment(file.originalFilename ?: throw NullPointerException(), file)
            javaMailSender.send(message)
        } catch (e: MessagingException) {
            throw EmailSendException()
        }
    }

}