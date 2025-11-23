package com.example.pagina.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) para la tabla de productos.
 * Define los métodos para interactuar con la base de datos de productos.
 */
@Dao
interface ProductDao {

    /**
     * Inserta un producto en la tabla. Si el producto ya existe, lo reemplaza.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    /**
     * Obtiene todos los productos de la tabla, ordenados por ID.
     * Devuelve un Flow, lo que significa que la UI se actualizará automáticamente
     * cada vez que los datos cambien.
     */
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAllProducts(): Flow<List<Product>>

    /**
     * Elimina un producto de la tabla usando su ID.
     */
    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: Int)

    /**
     * Elimina todos los productos de la tabla.
     */
    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}
