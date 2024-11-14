package org.sopt.and.data.datasource

import android.content.SharedPreferences
import jakarta.inject.Inject
import org.sopt.and.data.di.UserSharedPreference

internal class UserDataSource @Inject constructor(
    @UserSharedPreference private val userSharedPreference: SharedPreferences
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

    companion object {
        private const val ID = "id"
        private const val PASSWORD = "password"
        private const val DEFAULT_STRING = ""
    }
}