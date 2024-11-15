package org.sopt.and.data.dto.response


import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.SignInResponse

@Serializable
internal data class SignInResponseDto(
    val token: String
) {
    fun toDomainModel(): SignInResponse {
        return SignInResponse(
            token = token
        )
    }
}
