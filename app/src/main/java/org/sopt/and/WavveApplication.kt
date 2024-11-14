package org.sopt.and

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.presentation.util.PreferenceUtil

@HiltAndroidApp
class WavveApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceUtil.init(this)
    }
}