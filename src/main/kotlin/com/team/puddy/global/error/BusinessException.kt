package com.team.puddy.global.error

sealed class BusinessException(
    open val errorCode: ErrorCode
) : RuntimeException(errorCode.message)

data class UserNotFoundException(
    override val errorCode: ErrorCode = ErrorCode.USER_NOT_FOUND
) : BusinessException(errorCode)

data class InvalidPasswordException(
    override val errorCode: ErrorCode = ErrorCode.INVALID_PASSWORD
) : BusinessException(errorCode)

data class DuplicateRegisterException(
    override val errorCode: ErrorCode = ErrorCode.DUPLICATE_REGISTER
) : BusinessException(errorCode)

data class QuestionNotFoundException(
    override val errorCode: ErrorCode = ErrorCode.QUESTION_NOT_FOUND
) : BusinessException(errorCode)

data class TokenVerifyException(
    override val errorCode: ErrorCode = ErrorCode.TOKEN_VERIFY_FAIL
) : BusinessException(errorCode)

data class RefreshTokenNotFoundException(
    override val errorCode: ErrorCode = ErrorCode.REFRESH_TOKEN_NOT_FOUND
) : BusinessException(errorCode)

data class UnauthorizedException(
    override val errorCode: ErrorCode = ErrorCode.UNAUTHORIZED
) : BusinessException(errorCode)

data class EmailSendException(
    override val errorCode: ErrorCode = ErrorCode.EMAIL_SEND_FAIL
) : BusinessException(errorCode)

