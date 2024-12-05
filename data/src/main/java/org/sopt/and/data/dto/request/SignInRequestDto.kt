package org.sopt.and.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
internal data class SignInRequestDto(
    val username: String,
    val password: String
)