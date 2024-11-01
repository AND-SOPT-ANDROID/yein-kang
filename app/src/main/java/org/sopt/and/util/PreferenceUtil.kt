package org.sopt.and.util

import android.content.Context
import android.content.SharedPreferences
import org.sopt.and.util.KeyUtil.ID
import org.sopt.and.util.KeyUtil.PASSWORD
import org.sopt.and.util.KeyUtil.PREF_NAME

object PreferenceUtil  {
    private lateinit var preference: SharedPreferences

    fun init(context: Context) {
        if(!::preference.isInitialized){
            preference = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE
            )
        }
    }

    var id: String
        get() = preference.getString(ID, "").toString()
        set(value) = preference.edit().putString(ID, value).apply()

    var password: String
        get() = preference.getString(PASSWORD, "").toString()
        set(value) = preference.edit().putString(PASSWORD, value).apply()

    fun clearIdPassword() {
        id = ""
        password = ""
        preference.edit().clear().apply()
    }
}
