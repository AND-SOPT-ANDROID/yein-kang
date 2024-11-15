package org.sopt.and.data.repository

import jakarta.inject.Inject
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.dto.request.toRequestBody
import org.sopt.and.domain.model.SignUp
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.exception.Result

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override fun saveUser(id: String, password: String) {
        userDataSource.id = id
        userDataSource.password = password
    }

    override fun clearIdPassword() {
        userDataSource.clearIdPassword()
    }

    override fun getId(): String {
        return userDataSource.id
    }

    override suspend fun signUp(user: User): Result<SignUp> {
        return userDataSource.signUp(user.toRequestBody())
    }

}