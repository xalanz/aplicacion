package com.example.pagina.api

data class Product(
    val id: Long? = null, // Cambiado a Long? para permitir valores nulos
    val name: String,
    val categories: String,
    val price: Int,
    val image: String,
    val stock: Boolean,
    val discount: Boolean,
    val stars: Int
)