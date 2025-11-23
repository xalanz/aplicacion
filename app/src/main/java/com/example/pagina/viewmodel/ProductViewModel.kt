package com.example.pagina.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagina.data.local.Product
import com.example.pagina.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar la lógica de negocio de los productos en el carrito.
 */
class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    /**
     * Un Flow que expone la lista de todos los productos en el carrito.
     * La UI observará este Flow para actualizarse automáticamente.
     */
    val allProductsInCart: Flow<List<Product>> = repository.allProducts

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
