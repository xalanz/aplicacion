package com.example.pagina.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pagina.R
import com.example.pagina.data.local.Product
import com.example.pagina.navegacion.AppRutas
import com.example.pagina.viewmodel.ProductViewModel

val availableProducts = listOf(
    Product(productName = "Teclado Gamer", price = 69.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.teclado_1),
    Product(productName = "Monitor Curvo", price = 299.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.monitor1),
    Product(productName = "Mousepad XL", price = 29.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.mauspda1),
    Product(productName = "Audífonos Pro", price = 129.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.audifono2),
    Product(productName = "Monitor 24 pulgadas", price = 199.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.monito2),
    Product(productName = "Teclado Compacto", price = 89.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.teclado2),
    Product(productName = "Producto Extra", price = 79.99, imageUrl = "android.resource://com.example.pagina/" + R.drawable.teclado_1) // Re-usando una imagen
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaScreen(navController: NavController, productViewModel: ProductViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos", color = Color.White) },
                actions = {
                    IconButton(onClick = { navController.navigate(AppRutas.Cart.route) }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Ver carrito", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1F222A)
                )
            )
        },
        containerColor = Color(0xFF1F222A)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(availableProducts) { product ->
                ProductoCard(product = product, onAddToCart = {
                    productViewModel.addProductToCart(product)
                })
            }
        }
    }
}

@Composable
fun ProductoCard(product: Product, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2D37))
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = product.productName,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.7f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = product.productName, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "$${String.format("%.2f", product.price)}", fontSize = 18.sp, color = Color.Gray)
                }
                Button(
                    onClick = onAddToCart,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF6A1BBE),
                                        Color(0xFF8A2BE2)
                                    )
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Añadir al Carrito", color = Color.White)
                    }
                }
            }
        }
    }
}
