package com.example.pagina.data.repository

import com.example.pagina.data.local.User
import com.example.pagina.data.local.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDao) {
    val users: Flow<List<User>> = dao.getAllUsers()
    val latestUser: Flow<User?> = dao.getLatestUser()

    suspend fun getUserById(userId: Long): User? {
        return dao.getUserById(userId)
    }

    // --- NUEVA FUNCIÓN DE LOGIN ---
    // Es una función suspendida porque interactúa con la base de datos.
    suspend fun login(email: String, password: String): User? {
        return dao.login(email, password)
    }

    suspend fun insert(user: User) = dao.insertUser(user)
    suspend fun update(user: User) = dao.updateUser(user)
    suspend fun delete(user: User) = dao.deleteUser(user)
}
