package org.sopt.and.domain.repository

import org.sopt.and.domain.model.SignUpResponse
import org.sopt.and.domain.model.User
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.MyHobbyResponse

interface UserRepository {
    fun saveUser(id: String, password: String)
    fun saveToken(token: String)
    fun clearUserPreference()
    fun getToken(): String
    suspend fun signUp(user: User): Result<SignUpResponse>
    suspend fun getMyHobby(token: String): Result<MyHobbyResponse>
}