package com.example.pagina.api.repository

import com.example.pagina.api.model.Product
import com.example.pagina.api.remote.RetrofitInstance

open class PostRepository {
    open suspend fun getProduct(): List<Product>{
        return RetrofitInstance.api.getAllProducts()
    }
}
