package org.sopt.and.data.service

import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.SignUpRequestDto
import org.sopt.and.data.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface UserService {
    @POST("user")
    suspend fun signUp(
        @Body request: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

}