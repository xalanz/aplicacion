package com.example.pagina.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pagina.navegacion.AppRutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NosotrosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { navController.navigate(AppRutas.Home.route) }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                Spacer(Modifier.weight(1f))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sobre Nosotros", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Somos una tienda dedicada a ofrecer productos de alta calida reiser ache pe y brindar una atencion a factorio fans hola.",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            ProfileCard(
                imageVector = Icons.Default.Person, // Placeholder, replace with your image: R.drawable.luis_paredes
                name = "Luis Paredes",
                description = """Encargado de la fabrica
Apasionado por armar el main bus en factorio."""
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileCard(
                imageVector = Icons.Default.Person, // Placeholder, replace with your image: R.drawable.lucas_olmedo
                name = "Lucas olmedo",
                description = """Exterminador profesional
Responsable de la gestión y la visión estratégica de expansion de la fabrica 'Mata bichos nomas'."""
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Conecta con nosotros en nuestras redes sociales")
        }
    }
}

@Composable
fun ProfileCard(imageVector: ImageVector, name: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = imageVector,
                contentDescription = name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = description, fontSize = 14.sp)
            }
        }
    }
}
