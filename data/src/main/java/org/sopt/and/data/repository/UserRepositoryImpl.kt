package org.sopt.and.data.repository

import jakarta.inject.Inject
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.dto.request.toRequestBody
import org.sopt.and.domain.model.SignUpResponse
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.MyHobbyResponse

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override fun saveUser(id: String, password: String) {
        userDataSource.id = id
        userDataSource.password = password
    }

    override fun saveToken(token: String) {
        userDataSource.token = token
    }

    override fun clearUserPreference() {
        userDataSource.clearUserPreference()
    }

    override fun getToken(): String {
        return userDataSource.token
    }

    override suspend fun signUp(user: User): Result<SignUpResponse> {
        return userDataSource.signUp(user.toRequestBody())
    }

    override suspend fun getMyHobby(token: String): Result<MyHobbyResponse> {
        return userDataSource.getMyHobby(token)
    }

}