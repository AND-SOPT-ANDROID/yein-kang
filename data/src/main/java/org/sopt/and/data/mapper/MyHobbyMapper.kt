package org.sopt.and.data.mapper

import org.sopt.and.data.dto.response.MyHobbyResponseDto
import org.sopt.and.domain.model.MyHobby

internal fun MyHobbyResponseDto.toDomainModel(): MyHobby {
    return MyHobby(
        hobby = hobby
    )
}