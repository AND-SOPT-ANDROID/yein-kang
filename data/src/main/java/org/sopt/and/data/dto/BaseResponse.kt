package org.sopt.and.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class BaseResponse<T>(
    val result: T
)
