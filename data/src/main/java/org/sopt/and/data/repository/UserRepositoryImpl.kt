package org.sopt.and.data.repository

import jakarta.inject.Inject
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.domain.repository.UserRepository

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

}