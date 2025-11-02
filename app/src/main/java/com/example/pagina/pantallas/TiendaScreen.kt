package com.example.pagina.pantallas

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pagina.R

/**
 * Define la estructura de datos para un producto.
 * Usar una "data class" es ideal para guardar y gestionar datos.
 */
data class Producto(val name: String, val price: String, @get:DrawableRes val imageRes: Int)

/**
 * Pantalla de la Tienda, que muestra una lista de productos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaScreen(navController: NavController) {
    // Lista de productos que se mostrarán en la tienda.
    val productList = listOf(
        Producto("Teclado Gamer", "$69.99", R.drawable.teclado_1),
        Producto("Monitor Curvo", "$299.99", R.drawable.monitor1),
        Producto("Mousepad XL", "$29.99", R.drawable.mauspda1),
        Producto("Audífonos Pro", "$129.99", R.drawable.audifono2),
        Producto("Monitor 24 pulgadas", "$199.99", R.drawable.monito2),
        Producto("Teclado Compacto", "$89.99", R.drawable.teclado2),
        Producto("Producto Extra", "$79.99", R.drawable.teclado_1) // Re-usando una imagen
    )

    // Scaffold proporciona la estructura básica de Material Design (barra superior, etc.).
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tienda") },
                // Icono para volver a la pantalla anterior.
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        // LazyColumn es una lista desplazable que solo renderiza los elementos visibles,
        // lo que la hace muy eficiente para listas largas.
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // "items" toma la lista de productos y crea un Composable para cada uno.
            items(productList) { product ->
                ProductoCard(name = product.name, price = product.price, imageRes = product.imageRes)
            }
        }
    }
}

/**
 * Composable que define cómo se ve una tarjeta de producto individual.
 */
@Composable
fun ProductoCard(name: String, price: String, @DrawableRes imageRes: Int) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Muestra la imagen del producto.
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name, // Descripción para accesibilidad.
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Asegura que la imagen sea cuadrada.
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop // Escala la imagen para que llene el espacio sin deformarse.
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = price, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Lógica para añadir al carrito */ },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Añadir al Carrito")
                }
            }
        }
    }
}
