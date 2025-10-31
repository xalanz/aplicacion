package com.example.pagina.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pagina.registro_inicio.InicioSesionScreen
import com.example.pagina.registro_inicio.RegistroScreen
import com.example.pagina.registro_inicio.WelcomeScreen

/**
 * Gestiona la navegación principal de la aplicación.
 * Este componente es el "cerebro" que decide qué pantalla mostrar en cada momento.
 */
@Composable
fun AppNavigation() {
    // 1. CREAR EL CONTROLADOR DE NAVEGACIÓN
    // `rememberNavController` crea y recuerda un NavController.
    // Este objeto es el que nos permite movernos entre pantallas (con `navigate`).
    val navController = rememberNavController()

    // 2. DEFINIR EL GRAFO DE NAVEGACIÓN (NavHost)
    // `NavHost` es el contenedor donde se mostrarán las diferentes pantallas.
    // `startDestination` define la primera pantalla que se verá al abrir la app.
    NavHost(navController = navController, startDestination = AppRorutas.Welcome.route) {
        
        // 3. ASOCIAR RUTAS A PANTALLAS
        // `composable` se usa para definir una pantalla dentro del grafo de navegación.
        // Cada `composable` asocia una ruta (un String) con una pantalla (un Composable).

        // Pantalla de Bienvenida
        composable(route = AppRorutas.Welcome.route) {
            // Cuando la app navegue a la ruta "/welcome", se mostrará esta pantalla.
            WelcomeScreen(navController)
        }

        // Pantalla de Inicio de Sesión (Login)
        composable(route = AppRorutas.Login.route) {
            // Cuando la app navegue a la ruta "/Login", se mostrará esta pantalla.
            InicioSesionScreen(navController)
        }

        // Pantalla de Registro
        composable(route = AppRorutas.Registro.route) {
            // Cuando la app navegue a la ruta "/registro", se mostrará esta pantalla.
            RegistroScreen(navController = navController, modifier = Modifier)
        }
    }
}
