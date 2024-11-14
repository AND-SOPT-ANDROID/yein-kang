package org.sopt.and.data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.UserDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Singleton
    @Provides
    fun provideUserDataSource(
        @UserSharedPreference userSharedPreference: SharedPreferences
    ): UserDataSource = UserDataSource(userSharedPreference)

}