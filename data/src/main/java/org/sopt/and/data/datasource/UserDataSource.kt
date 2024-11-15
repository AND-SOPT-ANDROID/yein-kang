package org.sopt.and.data.datasource

import android.content.SharedPreferences
import jakarta.inject.Inject
import org.sopt.and.data.common.execute
import org.sopt.and.data.di.UserSharedPreference
import org.sopt.and.data.dto.request.SignUpRequest
import org.sopt.and.data.service.UserService
import org.sopt.and.domain.exception.Result
import org.sopt.and.domain.model.SignUpRequest

internal class UserDataSource @Inject constructor(
    @UserSharedPreference private val userSharedPreference: SharedPreferences,
    private val userService: UserService
) {

    var id: String
        get() = userSharedPreference.getString(ID, "").toString()
        set(value) = userSharedPreference.edit().putString(ID, value).apply()

    var password: String
        get() = userSharedPreference.getString(PASSWORD, "").toString()
        set(value) = userSharedPreference.edit().putString(PASSWORD, value).apply()

    fun clearIdPassword() {
        id = DEFAULT_STRING
        password = DEFAULT_STRING
        userSharedPreference.edit().clear().apply()
    }

    suspend fun signUp(request: SignUpRequest): Result<org.sopt.and.domain.model.SignUpRequest> = execute {
        userService.signUp(request).result.toDomainModel()
    }

    companion object {
        private const val ID = "id"
        private const val PASSWORD = "password"
        private const val DEFAULT_STRING = ""
    }
}