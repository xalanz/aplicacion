package com.example.pagina.data.repository

import com.example.pagina.data.local.Product
import com.example.pagina.data.local.ProductDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para gestionar las operaciones de los productos.
 * Actúa como intermediario entre el ViewModel y la fuente de datos (ProductDao).
 */
class ProductRepository(private val productDao: ProductDao) {

    /**
     * Un Flow que emite la lista completa de productos cada vez que cambia.
     */
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()

    /**
     * Inserta un nuevo producto en la base de datos.
     */
    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     */
    suspend fun deleteProductById(productId: Int) {
        productDao.deleteProductById(productId)
    }

    /**
     * Elimina todos los productos de la base de datos.
     */
    suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }
}
