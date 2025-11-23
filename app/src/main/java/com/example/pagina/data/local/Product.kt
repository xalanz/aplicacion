package com.example.pagina.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa la tabla "products" en la base de datos de Room.
 *
 * @param id La clave primaria única para cada producto.
 * @param productName El nombre del producto.
 * @param price El precio del producto.
 * @param imageUrl La URL de la imagen del producto.
 */
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productName: String,
    val price: Double,
    val imageUrl: String
)
