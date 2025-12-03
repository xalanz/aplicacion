package com.example.pagina.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pagina.viewmodel.ProductViewModel
import com.example.pagina.api.model.Product as ApiProduct
import com.example.pagina.navegacion.AppRutas

@Composable
fun AddProductScreen(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf(true) }
    var discount by remember { mutableStateOf(false) }
    var stars by remember { mutableStateOf("") }

    val createStatus by productViewModel.createProductStatus.collectAsState()

    LaunchedEffect(createStatus) {
        if (createStatus == true) {
            navController.navigate(AppRutas.Tienda.route) {
                popUpTo(AppRutas.Home.route) { inclusive = true }
            }
            productViewModel.resetCreateProductStatus()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = categories,
            onValueChange = { categories = it },
            label = { Text("Categorías") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = image,
            onValueChange = { image = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("En stock")
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = stock, onCheckedChange = { stock = it })
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("En descuento")
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = discount, onCheckedChange = { discount = it })
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = stars,
            onValueChange = { stars = it },
            label = { Text("Estrellas") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val product = ApiProduct(
                    // El ID se omite (será nulo) para que el servidor lo genere
                    name = name,
                    categories = categories,
                    price = price.toIntOrNull() ?: 0,
                    image = image,
                    stock = stock,
                    discount = discount,
                    stars = stars.toIntOrNull() ?: 0
                )
                productViewModel.createProduct(product)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Producto")
        }
    }
}
