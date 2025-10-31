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
import com.example.pagina.navegacion.AppRorutas

/**
 * Define la pantalla de bienvenida de la aplicación.
 * Es la primera pantalla que ve el usuario y le ofrece las opciones de iniciar sesión o registrarse.
 *
 * @param navController El controlador de navegación que permite moverse a otras pantallas.
 */
@Composable
fun WelcomeScreen(navController: NavController) {
    // Column es un componente que apila a sus hijos verticalmente (uno debajo del otro).
    Column(
        // El modifier se usa para configurar la apariencia y el comportamiento del componente.
        modifier = Modifier.fillMaxSize(), // Hace que la columna ocupe todo el espacio disponible en la pantalla.
        verticalArrangement = Arrangement.Center, // Centra los botones verticalmente en la pantalla.
        horizontalAlignment = Alignment.CenterHorizontally // Centra los botones horizontalmente en la pantalla.
    ) {

        // --- BOTÓN DE LOGIN ---
        Button(onClick = {
            // Define la acción que se ejecuta al pulsar el botón.
            // `navController.navigate` se usa para navegar a otra pantalla.
            // AppRorutas.Login.route contiene la ruta de destino (p. ej., "/Login").
            navController.navigate(AppRorutas.Login.route)
        }) {
            Text("Login") // El texto que se muestra dentro del botón.
        }

        // Spacer crea un espacio vacío vertical de 16.dp de altura para separar los botones.
        Spacer(modifier = Modifier.height(16.dp))

        // --- BOTÓN DE REGISTRO ---
        Button(onClick = { 
            // Al pulsar este botón, navega a la pantalla de registro.
            navController.navigate(AppRorutas.Registro.route) 
        }) {
            Text("Registro")
        }
    }
}