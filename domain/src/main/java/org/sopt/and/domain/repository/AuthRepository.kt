package org.sopt.and.domain.repository

import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.UserCredentials
import org.sopt.and.domain.model.AuthToken

interface AuthRepository {
    suspend fun signIn(request: UserCredentials): Result<AuthToken>
}