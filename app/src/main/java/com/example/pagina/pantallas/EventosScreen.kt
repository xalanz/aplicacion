package com.example.pagina.pantallas

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.pagina.R

data class Evento(val name: String, val description: String, val imageUrl: String)

val tftEvents = listOf(
    Evento(
        name = "Coliseo KO",
        description = "Lanzamos el Coliseo KO con un parche espectacular con el Tesoro de Choncc y un montón de mejoras tanto para KO como para Uncharted Realms! Pero la acción apenas comienza; según la leyenda, un nuevo set llegará en el próximo parche, así que no te pierdas la historia en un solo vistazo",
        imageUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt5a56d205051e605d/631a52dc6678230e819f79bd/TFT_Pass_And_More_Article_Header.jpg"
    ),
    Evento(
        name = "Pase Beta de TFT",
        description = "Desbloquea recompensas exclusivas con el nuevo pase de batalla.",
        imageUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt5a56d205051e605d/631a52dc6678230e819f79bd/TFT_Pass_And_More_Article_Header.jpg"
    ),
    Evento(
        name = "Nuevos Íconos de Invocador",
        description = "Colecciona los íconos exclusivos de la última temporada.",
        imageUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt3b782b5f778d9b1c/631a527e466e330e77d29c42/TFT_Dev_Drop_Sept_Article_Header.jpg"
    ),
    Evento(
        name = "Minileyendas: Espíritu Floral",
        description = "Descubre las nuevas y adorables minileyendas del set.",
        imageUrl = "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/bltf13d544062a433f4/633184f4b23869112267b25b/TFT_Set7.5_LittleLegends_Article_Header.jpg"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eventos de TFT", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1F222A))
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
            items(tftEvents) { evento ->
                EventoCard(evento = evento)
            }
        }
    }
}

@Composable
fun EventoCard(evento: Evento) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2D37))
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(evento.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = evento.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.7f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(text = evento.name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = evento.description, fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}
