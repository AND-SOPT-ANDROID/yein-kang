package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.SignUpRequest

@Serializable
internal data class SignUpResponseDto(
    @SerialName("no")
    val userNumber: Int
){
    fun toDomainModel(): SignUpRequest {
        return SignUpRequest(
            userNumber = userNumber
        )
    }
}