package com.example.pagina.api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.pagina.api.repository.PostRepository
import com.example.pagina.api.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class PostViewModel(
    private val repository: PostRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _postList = MutableStateFlow<List<Product>>(emptyList())
    open val postList: StateFlow<List<Product>> = _postList

    init { fetchPosts() }

    open fun fetchPosts() {
        viewModelScope.launch(dispatcher) {
            try {
                _postList.value = repository.getProduct()
            } catch (e: Exception) { }
        }
    }
}
