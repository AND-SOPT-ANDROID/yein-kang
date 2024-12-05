package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.RegisteredUser

@Serializable
internal data class SignUpResponseDto(
    @SerialName("no")
    val userNumber: Int
)