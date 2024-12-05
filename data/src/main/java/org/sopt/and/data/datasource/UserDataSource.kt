package org.sopt.and.data.datasource

import android.content.SharedPreferences
import jakarta.inject.Inject
import org.sopt.and.data.common.execute
import org.sopt.and.data.dto.request.SignUpRequestDto
import org.sopt.and.data.mapper.toDomainModel
import org.sopt.and.data.service.UserService
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.MyHobby
import org.sopt.and.domain.model.RegisteredUser

internal class UserDataSource @Inject constructor(
    private val userSharedPreference: SharedPreferences,
    private val userService: UserService
) {

    var id: String
        get() = userSharedPreference.getString(ID, "").toString()
        set(value) = userSharedPreference.edit().putString(ID, value).apply()

    var password: String
        get() = userSharedPreference.getString(PASSWORD, "").toString()
        set(value) = userSharedPreference.edit().putString(PASSWORD, value).apply()

    var token: String
        get() = userSharedPreference.getString(TOKEN, "").toString()
        set(value) = userSharedPreference.edit().putString(TOKEN, value).apply()

    fun clearUserPreference() {
        id = DEFAULT_STRING
        password = DEFAULT_STRING
        token = DEFAULT_STRING
        userSharedPreference.edit().clear().apply()
    }

    suspend fun signUp(request: SignUpRequestDto): Result<RegisteredUser> = execute {
        userService.signUp(request).result.toDomainModel()
    }

    suspend fun getMyHobby(token: String): Result<MyHobby> = execute {
        userService.getMyHobby(token).result.toDomainModel()
    }

    companion object {
        private const val ID = "id"
        private const val PASSWORD = "password"
        private const val TOKEN = "token"
        private const val DEFAULT_STRING = ""
    }
}