package org.sopt.and.data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.AuthDataSource
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.service.AuthService
import org.sopt.and.data.service.UserService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Singleton
    @Provides
    fun provideUserDataSource(
        userSharedPreference: SharedPreferences,
        userService: UserService
    ): UserDataSource = UserDataSource(userSharedPreference, userService)

    @Singleton
    @Provides
    fun provideAuthDataSource(
        authService: AuthService
    ): AuthDataSource = AuthDataSource(authService)


}