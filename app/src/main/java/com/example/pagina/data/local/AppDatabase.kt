package com.example.pagina.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * La clase principal de la base de datos de Room.
 *
 * @property userDao El DAO para acceder a la tabla de usuarios.
 * @property productDao El DAO para acceder a la tabla de productos.
 */
@Database(entities = [User::class, Product::class], version = 4, exportSchema = false) // Incrementada la versión por el cambio de esquema
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}
