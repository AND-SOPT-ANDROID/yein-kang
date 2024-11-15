package org.sopt.and.data.dto.response

import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.MyHobbyResponse

@Serializable
data class MyHobbyResponseDto(
    val hobby: String
) {
    fun toDomainModel(): MyHobbyResponse {
        return MyHobbyResponse(
            hobby = hobby
        )
    }
}
