package com.example.pagina.registro_inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pagina.navegacion.AppRutas

/**
 * Pantalla de bienvenida, la primera que ve el usuario.
 * Ofrece las opciones principales de navegación inicial.
 */
@Composable
fun WelcomeScreen(navController: NavController) {
    // Column centra los elementos vertical y horizontalmente.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¡Bienvenido a Nuestra Tienda!")

        Spacer(modifier = Modifier.height(32.dp))

        // Botón que navega a la pantalla de inicio de sesión.
        Button(onClick = { navController.navigate(AppRutas.Login.route) }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que navega a la pantalla de registro.
        Button(onClick = { navController.navigate(AppRutas.Registro.route) }) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que permite a los usuarios entrar directamente a la tienda.
        Button(onClick = { navController.navigate(AppRutas.Tienda.route) }) {
            Text("Entrar como Invitado")
        }
    }
}
