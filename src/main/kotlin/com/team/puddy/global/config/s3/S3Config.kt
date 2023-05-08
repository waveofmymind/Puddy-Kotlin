package com.team.puddy.global.config.s3

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {

    @Value("\${cloud.aws.credentials.access-key}")
    private val ACCESS_KEY: String? = null

    @Value("\${cloud.aws.credentials.secret-key}")
    private val SECRET_KEY: String? = null

    @Value("\${cloud.aws.region.static}")
    private val REGION: String? = null // Bucket Region

    @Bean
    fun amazonS3Client(): AmazonS3Client? {
        val awsCreds = BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .withRegion(REGION)
            .build() as AmazonS3Client
    }

}