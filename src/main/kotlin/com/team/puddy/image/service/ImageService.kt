package com.team.puddy.image.service

import com.team.puddy.global.config.s3.S3UpdateUtil
import com.team.puddy.image.domain.Image
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.function.BiFunction


@Service
@Transactional
class ImageService(
    private val s3UpdateUtil: S3UpdateUtil
) {

    @Throws(IOException::class)
    fun uploadImage(file: MultipartFile, uploadFunction: BiFunction<MultipartFile, String, String>): Image {
        val savedFileName: String = s3UpdateUtil.createSavedFileName(file.originalFilename!!)
        val imagePath = uploadFunction.apply(file, savedFileName)
        return Image(
            imagePath = imagePath,
            originalName = file.originalFilename!!,
            savedName = savedFileName
        )
    }

    @Throws(IOException::class)
    fun uploadImageList(files: List<MultipartFile>?) = files?.map { file ->
        uploadImage(file, BiFunction { file, savedFileName ->
            s3UpdateUtil.uploadToS3(file, savedFileName)
        })
    } ?: mutableListOf()
}

