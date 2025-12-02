package com.example.pagina.data.repository

import com.example.pagina.api.remote.ApiService
import com.example.pagina.api.remote.RetrofitInstance
import com.example.pagina.data.local.Product
import com.example.pagina.api.model.Product as ApiProduct
import com.example.pagina.data.local.ProductDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para gestionar las operaciones de los productos.
 * Actúa como intermediario entre el ViewModel y las fuentes de datos (local y remota).
 */
class ProductRepository(
    private val productDao: ProductDao,
    private val apiService: ApiService = RetrofitInstance.api
) {

    /**
     * Un Flow que emite la lista completa de productos locales.
     */
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()

    /**
     * Crea un nuevo producto de forma remota a través de la API.
     */
    suspend fun createRemoteProduct(product: ApiProduct) {
        apiService.createProduct(product)
    }

    /**
     * Inserta un nuevo producto en la base de datos local.
     */
    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    /**
     * Elimina un producto de la base de datos local por su ID.
     */
    suspend fun deleteProductById(productId: Int) {
        productDao.deleteProductById(productId)
    }

    /**
     * Elimina todos los productos de la base de datos local.
     */
    suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }
}
