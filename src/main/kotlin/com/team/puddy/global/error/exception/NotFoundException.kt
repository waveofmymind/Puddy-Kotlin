package com.team.puddy.global.error.exception

import com.team.puddy.global.error.ErrorCode

class NotFoundException(errorCode: ErrorCode) : BusinessException(errorCode) {
}