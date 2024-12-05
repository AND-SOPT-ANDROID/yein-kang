package org.sopt.and.data.repository

import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.mapper.toRequestBody
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.UserCredentials
import org.sopt.and.domain.model.AuthToken
import org.sopt.and.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun signIn(request: UserCredentials): Result<AuthToken> {
        return authDataSource.signIn(request.toRequestBody())
    }
}