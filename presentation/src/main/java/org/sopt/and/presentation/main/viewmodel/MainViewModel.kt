package org.sopt.and.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.navigation.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    fun getStartDestination(): Route {
        return if(userRepository.getId().isNotEmpty()){
            Route.Home
        } else {
            Route.SignIn
        }
    }
}