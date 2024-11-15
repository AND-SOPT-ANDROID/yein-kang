package org.sopt.and.data.service

import org.sopt.and.data.dto.request.SignInRequestDto
import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthService {
    @POST("login")
    suspend fun signIn(
        @Body request: SignInRequestDto
    ): BaseResponse<SignInResponseDto>
}