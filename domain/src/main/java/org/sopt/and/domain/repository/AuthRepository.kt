package org.sopt.and.domain.repository

import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.SignInRequest
import org.sopt.and.domain.model.SignInResponse

interface AuthRepository {
    suspend fun signIn(request: SignInRequest): Result<SignInResponse>
}