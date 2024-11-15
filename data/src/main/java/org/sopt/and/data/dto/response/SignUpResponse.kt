package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.SignUp

@Serializable
internal data class SignUpResponse(
    @SerialName("no")
    val userNumber: Int
){
    fun toDomainModel(): SignUp {
        return SignUp(
            userNumber = userNumber
        )
    }
}