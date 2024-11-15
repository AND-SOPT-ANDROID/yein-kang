package org.sopt.and.domain.repository

import org.sopt.and.domain.model.SignUpResponse
import org.sopt.and.domain.model.User
import org.sopt.and.domain.exception.Result

interface UserRepository {
    fun saveUser(id: String, password: String)
    fun clearIdPassword()
    fun getId(): String
    suspend fun signUp(user: User): Result<SignUpResponse>
}