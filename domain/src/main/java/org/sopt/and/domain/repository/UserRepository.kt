package org.sopt.and.domain.repository

interface UserRepository {
    fun saveUser(id: String, password: String)
    fun clearIdPassword()
    fun getId(): String
}