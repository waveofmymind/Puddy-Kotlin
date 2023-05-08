package com.team.puddy.global.config.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.team.puddy.image.domain.Image
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*


@Component
class S3UpdateUtil(
    private val amazonS3Client: AmazonS3Client
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val BUCKET: String? = null

    fun createSavedFileName(originalName: String): String {
        return UUID.randomUUID().toString().plus(originalName)
    }

    @Throws(IOException::class)
    fun uploadToS3(file: MultipartFile, fileName: String, folderName: String): String? {
        val objectMetaData: ObjectMetadata = createMetaDate(file)
        // S3에 업로드
        amazonS3Client.putObject(
            PutObjectRequest(
                BUCKET,
                "$folderName/$fileName", file.inputStream, objectMetaData
            )
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return amazonS3Client.getUrl(BUCKET, "$folderName/$fileName").toString()
    }

    fun deleteImageFromS3(image: Image, folderName: String) {
        amazonS3Client.deleteObject(BUCKET, folderName + "/" + image.savedName)
    }

    fun createMetaDate(file: MultipartFile): ObjectMetadata {
        val objectMetaData = ObjectMetadata()
        objectMetaData.contentType = file.contentType
        objectMetaData.contentLength = file.size
        return objectMetaData
    }
}

