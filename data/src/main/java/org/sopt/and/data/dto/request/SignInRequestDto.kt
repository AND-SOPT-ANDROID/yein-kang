package org.sopt.and.data.dto.request

import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.SignInRequest

@Serializable
internal data class SignInRequestDto(
    val username: String,
    val password: String
)

internal fun SignInRequest.toRequestBody(): SignInRequestDto {
    return SignInRequestDto(
        username = username,
        password = password
    )
}