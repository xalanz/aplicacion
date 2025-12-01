package com.example.pagina.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagina.data.local.User
import com.example.pagina.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val users = repository.users.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(),
        emptyList()
    )

    val latestUser: StateFlow<User?> = repository.latestUser
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun loadUserById(userId: Long) {
        viewModelScope.launch {
            _currentUser.value = repository.getUserById(userId)
        }
    }

    // --- NUEVA FUNCIÓN DE LOGIN ---
    // Es una función suspendida porque interactúa con la base de datos.
    suspend fun login(email: String, password: String): User? {
        return repository.login(email, password)
    }

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