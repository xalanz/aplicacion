package com.example.pagina.registro_inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pagina.navegacion.AppRutas
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(navController: NavController) {

    // Colores inspirados en el diseño neomórfico del kit UI
    val fondo = Color(0xFF0A0B1F)    // gris pastel muy claro
    val sombraClara = Color.White
    val sombraOscura = Color(0xFFB7BEC8)
    val acento = Color(0xFF86A8E7)      // azul suave estilo neomórfico

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {

        // Tarjeta central con efecto neomórfico
        Surface(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    ambientColor = sombraOscura,
                    spotColor = sombraClara,
                    shape = RoundedCornerShape(28.dp)
                ),
            shape = RoundedCornerShape(28.dp),
            color = fondo
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🎮 Emoji grande
                Text(
                    text = "🎮",
                    fontSize = 90.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "¡Bienvenidos a leve-up!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFFFFFFFF)
                )

                Spacer(modifier = Modifier.height(40.dp))


                // Botón neumórfico reutilizable
                NeomorphButton(
                    text = "Iniciar Sesión",
                    background = fondo,
                    onClick = { navController.navigate(AppRutas.Login.route) }
                )

                Spacer(modifier = Modifier.height(20.dp))

                NeomorphButton(
                    text = "Registrarse",
                    background = fondo,
                    onClick = { navController.navigate(AppRutas.Registro.route) }
                )

                Spacer(modifier = Modifier.height(20.dp))

                NeomorphButton(
                    text = "Entrar como Invitado",
                    background = fondo,
                    onClick = { navController.navigate(AppRutas.Tienda.route) }
                )
            }
        }
    }
}

@Composable
fun NeomorphButton(
    text: String,
    background: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.White,
                spotColor = Color(0xFFBFC4CE)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF7B2FF7),  // Morado eléctrico
                        Color(0xFF9C4DFF),  // Morado brillante
                        Color(0xFFC084FC)   // Lila luminoso
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White)
    }
}
