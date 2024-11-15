package org.sopt.and.data.dto.request

import kotlinx.serialization.Serializable
import org.sopt.and.domain.model.User

@Serializable
data class SignUpRequest(
    val hobby: String,
    val password: String,
    val username: String
)

fun User.toRequestBody(): SignUpRequest {
    return SignUpRequest(
        hobby = hobby,
        password = password,
        username = username
    )
}