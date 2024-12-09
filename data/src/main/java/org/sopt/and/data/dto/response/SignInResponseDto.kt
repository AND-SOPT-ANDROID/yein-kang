package org.sopt.and.data.dto.response


import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.AuthToken

@Serializable
internal data class SignInResponseDto(
    val token: String
)
