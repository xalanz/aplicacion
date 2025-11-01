package com.example.pagina.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NosotrosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sobre Nosotros") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Somos una tienda dedicada a ofrecer productos de alta calidad de Reiser ACHE PE, brindando además una atención cercana viva el LOL.",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            ProfileCard(
                imageVector = Icons.Default.Person, // Placeholder, replace with your image: R.drawable.luis_paredes
                name = "Alan Thomas ",
                description = """jugador de lol que no sabe tirar el es smite."""
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileCard(
                imageVector = Icons.Default.Person, // Placeholder, replace with your image: R.drawable.lucas_olmedo
                name = "Dayelin benavides ",
                description = """'."""
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Conecta con nosotros en nuestras redes sociales")
        }
    }
}

@Composable
fun ProfileCard(imageVector: ImageVector, name: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
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
