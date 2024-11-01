package org.sopt.and

import android.app.Application
import org.sopt.and.util.PreferenceUtil

class WavveApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceUtil.init(this)
    }
}