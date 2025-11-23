package com.example.pagina.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagina.data.repository.ProductRepository

/**
 * Fábrica (Factory) para crear instancias de `ProductViewModel`.
 * Es necesaria porque nuestro ViewModel tiene un constructor con argumentos (el repositorio).
 */
class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
