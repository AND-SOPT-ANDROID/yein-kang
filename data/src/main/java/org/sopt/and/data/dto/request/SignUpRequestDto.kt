package org.sopt.and.data.dto.request

import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.User

@Serializable
internal data class SignUpRequestDto(
    val hobby: String,
    val password: String,
    val username: String
)