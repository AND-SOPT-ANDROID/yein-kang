package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.SignUpResponse

@Serializable
internal data class SignUpResponseDto(
    @SerialName("no")
    val userNumber: Int
){
    fun toDomainModel(): SignUpResponse {
        return SignUpResponse(
            userNumber = userNumber
        )
    }
}