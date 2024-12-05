package org.sopt.and.data.mapper

import org.sopt.and.data.dto.request.SignInRequestDto
import org.sopt.and.data.dto.response.SignInResponseDto
import org.sopt.and.domain.model.AuthToken
import org.sopt.and.domain.model.UserCredentials

internal fun UserCredentials.toRequestBody(): SignInRequestDto {
    return SignInRequestDto(
        username = username,
        password = password
    )
}

internal fun SignInResponseDto.toDomainModel(): AuthToken {
    return AuthToken(
        token = token
    )
}