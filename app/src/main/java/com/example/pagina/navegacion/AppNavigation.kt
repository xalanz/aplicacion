package com.example.pagina.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pagina.pantallas.HomeScreen
import com.example.pagina.pantallas.NosotrosScreen
import com.example.pagina.pantallas.ProfileScreen
import com.example.pagina.pantallas.SettingsScreen
import com.example.pagina.pantallas.TiendaScreen
import com.example.pagina.registro_inicio.InisioSeecion
import com.example.pagina.registro_inicio.Registro
import com.example.pagina.registro_inicio.WelcomeScreen
import com.example.pagina.viewmodel.UserViewModel

/**
 * Gestiona la navegación principal de la aplicación.
 * Es el "controlador de tráfico" que decide qué pantalla mostrar.
 */
@Composable
fun AppNavigation(viewModel: UserViewModel) {
    // El NavController es el responsable de gestionar la pila de navegación (back stack).
    val navController = rememberNavController()

    // NavHost es el contenedor donde se mostrarán las diferentes pantallas.
    // startDestination define la primera pantalla que se verá al abrir la app.
    NavHost(navController = navController, startDestination = AppRutas.Welcome.route) {

        // Cada "composable" asocia una ruta (de AppRutas) con una pantalla (un Composable).
        composable(route = AppRutas.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = AppRutas.Login.route) {
            InisioSeecion(navController, onLoginSuccess = {
                // Lógica de navegación tras un inicio de sesión exitoso.
                navController.navigate(AppRutas.Home.route) {
                    // Limpia la pila de navegación hasta la pantalla de bienvenida para que el usuario no pueda volver atrás.
                    popUpTo(AppRutas.Welcome.route) { inclusive = true }
                }
            })
        }

        composable(route = AppRutas.Registro.route) {
            // Se pasa el ViewModel a la pantalla de Registro para que pueda guardar los datos.
            Registro(navController = navController, viewModel = viewModel, modifier = Modifier)
        }

        composable(route = AppRutas.Home.route) {
            HomeScreen(navController)
        }

        composable(route = AppRutas.Profile.route) {
            // Se pasa el ViewModel a la pantalla de Perfil para que pueda mostrar los datos.
            ProfileScreen(viewModel = viewModel)
        }

        composable(route = AppRutas.Settings.route) {
            SettingsScreen()
        }

        composable(route = AppRutas.Nosotros.route) {
            NosotrosScreen(navController)
        }

        composable(route = AppRutas.Tienda.route) {
            TiendaScreen(navController)
        }
    }
}
