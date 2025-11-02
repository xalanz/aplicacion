package com.example.pagina.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagina.data.local.User
import com.example.pagina.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val users = repository.users.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(),
        emptyList()
    )

    // Expone el último usuario como un StateFlow
    val latestUser: StateFlow<User?> = repository.latestUser
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun addUser(name: String, apellidos: String, email: String, password: String, direccion: String) {
        viewModelScope.launch {
            val newUser = User(name = name, apellidos = apellidos, email = email, password = password, direccion = direccion)
            repository.insert(newUser)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.delete(user)
        }
    }

    fun updateUser(user: User, newName: String) {
        viewModelScope.launch {
            repository.update(user.copy(name = newName))
        }
    }
}