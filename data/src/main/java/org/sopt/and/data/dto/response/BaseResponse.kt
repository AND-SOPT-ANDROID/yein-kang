package org.sopt.and.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class BaseResponse<T>(
    val result: T
)
