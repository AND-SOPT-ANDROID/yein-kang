package org.sopt.and.data.datasource

import org.sopt.and.data.common.execute
import org.sopt.and.data.dto.request.SignInRequestDto
import org.sopt.and.data.mapper.toDomainModel
import org.sopt.and.data.service.AuthService
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.AuthToken
import javax.inject.Inject

internal class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun signIn(request: SignInRequestDto): Result<AuthToken> = execute {
        authService.signIn(request).result.toDomainModel()
    }

}