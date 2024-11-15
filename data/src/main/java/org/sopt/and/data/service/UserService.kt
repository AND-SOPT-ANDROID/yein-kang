package org.sopt.and.data.service

import org.sopt.and.data.dto.request.SignUpRequest
import org.sopt.and.data.dto.response.BaseResponse
import org.sopt.and.data.dto.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface UserService {
    @POST("user")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): BaseResponse<SignUpResponse>

}