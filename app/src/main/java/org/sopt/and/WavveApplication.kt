package org.sopt.and

import android.app.Application
import org.sopt.and.util.PreferenceUtil

class WavveApplication: Application() {

    private lateinit var prefs: PreferenceUtil

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)
    }
}