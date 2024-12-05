package org.sopt.and.data.mapper

import org.sopt.and.data.dto.request.SignUpRequestDto
import org.sopt.and.data.dto.response.SignUpResponseDto
import org.sopt.and.domain.model.RegisteredUser
import org.sopt.and.domain.model.User

internal fun User.toRequestBody(): SignUpRequestDto {
    return SignUpRequestDto(
        hobby = hobby,
        password = password,
        username = username
    )
}

internal fun SignUpResponseDto.toDomainModel(): RegisteredUser {
    return RegisteredUser(
        userNumber = userNumber
    )
}