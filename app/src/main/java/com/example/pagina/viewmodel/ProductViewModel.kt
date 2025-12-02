package com.example.pagina.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagina.api.model.Product as ApiProduct
import com.example.pagina.data.local.Product
import com.example.pagina.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar la lógica de negocio de los productos.
 */
class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _createProductStatus = MutableStateFlow<Boolean?>(null)
    val createProductStatus: StateFlow<Boolean?> = _createProductStatus.asStateFlow()

    /**
     * Un Flow que expone la lista de todos los productos en el carrito.
     * La UI observará este Flow para actualizarse automáticamente.
     */
    val allProductsInCart: Flow<List<Product>> = repository.allProducts

    /**
     * Crea un nuevo producto de forma remota.
     */
    fun createProduct(product: ApiProduct) = viewModelScope.launch {
        try {
            repository.createRemoteProduct(product)
            _createProductStatus.value = true
        } catch (e: Exception) {
            _createProductStatus.value = false
        }
    }

    fun resetCreateProductStatus() {
        _createProductStatus.value = null
    }

    /**
     * Añade un producto al carrito (lo inserta en la base de datos).
     */
    fun addProductToCart(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    /**
     * Elimina un producto del carrito usando su ID.
     */
    fun deleteProductPerId(productId: Int) = viewModelScope.launch {
        repository.deleteProductById(productId)
    }

    /**
     * Vacía completamente el carrito, eliminando todos los productos.
     */
    fun clearCart() = viewModelScope.launch {
        repository.deleteAllProducts()
    }
}
