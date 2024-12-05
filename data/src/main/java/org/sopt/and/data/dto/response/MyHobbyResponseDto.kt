package org.sopt.and.data.dto.response

import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.MyHobby

@Serializable
internal data class MyHobbyResponseDto(
    val hobby: String
)
