package com.example.pagina.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val apellidos: String,
    val email: String,
    val password: String,
    val direccion: String
)
