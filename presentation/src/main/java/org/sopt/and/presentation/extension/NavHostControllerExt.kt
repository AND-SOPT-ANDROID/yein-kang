package org.sopt.and.presentation.extension

import androidx.navigation.NavHostController
import org.sopt.and.presentation.util.KeyUtil.DEFAULT_STRING
import org.sopt.and.presentation.util.KeyUtil.ID
import org.sopt.and.presentation.util.KeyUtil.PASSWORD

fun NavHostController.getId(): String =
    this.currentBackStackEntry?.savedStateHandle?.get<String>(ID) ?: DEFAULT_STRING

fun NavHostController.getPassword(): String =
    this.currentBackStackEntry?.savedStateHandle?.get<String>(PASSWORD) ?: DEFAULT_STRING

fun NavHostController.setIdPassword(
    id: String,
    password: String
){
    this.previousBackStackEntry?.savedStateHandle?.apply {
        set(ID, id)
        set(PASSWORD, password)
    }
}