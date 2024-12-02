package org.sopt.and.data.repository

import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.dto.request.toRequestBody
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.SignInRequest
import org.sopt.and.domain.model.SignInResponse
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun signIn(request: SignInRequest): Result<SignInResponse> {
        return authDataSource.signIn(request.toRequestBody())
    }
}