package com.example.pagina.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * Pantalla que muestra información sobre la empresa o el proyecto.
 *
 * @param navController El controlador de navegación para manejar las acciones de navegación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NosotrosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sobre Nosotros") },
                navigationIcon = {
                    // Icono para volver a la pantalla anterior.
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        // Contenido principal de la pantalla con scroll vertical.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título de la sección.
            Text("Sobre Nosotros", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            // Texto descriptivo de la empresa.
            Text(
                text = "Somos una tienda dedicada a ofrecer productos de alta calidad y brindar una atención excepcional a nuestros clientes. Nos esforzamos por entregar la mejor experiencia de compra, " +
                        "cuidando cada detalle y seleccionando cuidadosamente nuestros producto viva el keznitdeus .",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta de perfil para el primer miembro del equipo.
            ProfileCard(
                imageVector = Icons.Default.Person, // Placeholder, replace with your image: R.drawable.luis_paredes
                name = "Alan Thomas ",
                description = "jugador de lol ."
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta de perfil para el segundo miembro del equipo.
            ProfileCard(
                imageVector = Icons.Default.Person, // Placeholder, replace with your image: R.drawable.lucas_olmedo
                name = "Dayeil ",
                description = "Exterminador profesional. Responsable de la gestión y la visión estratégica para  . Además, jugador de Valorant en mis tiempos libre'."
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Texto para conectar en redes sociales.
            Text(text = "Conecta con nosotros en nuestras redes sociales")
        }
    }
}

/**
 * Tarjeta de perfil que muestra la información de un miembro del equipo.
 *
 * @param imageVector El vector de imagen para el perfil.
 * @param name El nombre de la persona.
 * @param description Una breve descripción de la persona.
 */
@Composable
fun ProfileCard(imageVector: ImageVector, name: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de perfil.
            Image(
                imageVector = imageVector,
                contentDescription = name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Columna con el nombre y la descripción.
            Column {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = description)
            }
        }
    }
}