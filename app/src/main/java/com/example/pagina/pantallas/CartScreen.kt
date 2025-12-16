package com.example.pagina.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pagina.data.local.Product
import com.example.pagina.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, productViewModel: ProductViewModel) {
    val products by productViewModel.allProductsInCart.collectAsState(initial = emptyList())
    val groupedProducts = products.groupBy { it.productName }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            if (products.isNotEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    val totalPrice = products.sumOf { it.price }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("TOTAL", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("$${"%.2f".format(totalPrice)}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(onClick = { /* TODO: Implementar navegación a pago */ }, modifier = Modifier.weight(1f)) {
                            Text("Iniciar pago")
                        }
                        OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f)) {
                            Text("Seguir comprando")
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        if (products.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tu carrito está vacío 🕳️",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = groupedProducts.toList(),
                    key = { (name, _) -> name }
                ) { (_, productsInGroup) ->
                    val product = productsInGroup.first()
                    val quantity = productsInGroup.size

                    CartItem(
                        product = product,
                        quantity = quantity,
                        onQuantityChange = { newQuantity ->
                            val diff = newQuantity - quantity
                            if (diff > 0) {
                                repeat(diff) {
                                    val newProduct = product.copy(id = 0)
                                    productViewModel.addProductToCart(newProduct)
                                }
                            } else if (diff < 0) {
                                repeat(-diff) {
                                    val productToRemove = productsInGroup[it]
                                    productViewModel.deleteProductPerId(productToRemove.id)
                                }
                            }
                        },
                        onRemove = {
                            productsInGroup.forEach {
                                productViewModel.deleteProductPerId(it.id)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CartItem(
    product: Product,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = product.productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.productName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "$${"%.2f".format(product.price)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    OutlinedButton(
                        onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                        modifier = Modifier.size(32.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("-")
                    }
                    Text(
                        text = "$quantity",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    OutlinedButton(
                        onClick = { onQuantityChange(quantity + 1) },
                        modifier = Modifier.size(32.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("+")
                    }
                }
            }
        }
        TextButton(onClick = onRemove) {
            Text("Eliminar", color = MaterialTheme.colorScheme.error)
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.DarkGray)
    }
}
