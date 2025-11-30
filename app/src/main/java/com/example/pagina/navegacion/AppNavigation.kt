package com.example.pagina.navegacion

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pagina.pantallas.CartScreen
import com.example.pagina.pantallas.EventosScreen
import com.example.pagina.pantallas.HomeScreen
import com.example.pagina.pantallas.NosotrosScreen
import com.example.pagina.pantallas.ProfileScreen
import com.example.pagina.pantallas.SettingsScreen
import com.example.pagina.pantallas.TiendaScreen
import com.example.pagina.registro_inicio.InisioSeecion
import com.example.pagina.registro_inicio.Registro
import com.example.pagina.registro_inicio.WelcomeScreen
import com.example.pagina.viewmodel.ProductViewModel
import com.example.pagina.viewmodel.UserViewModel

@Composable
fun AppNavigation(
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel,
    sharedPreferences: SharedPreferences // Nuevo parámetro
) {
    val navController = rememberNavController()

    // Decide la ruta de inicio basada en si el usuario ya ha iniciado sesión
    val loggedInUserId = sharedPreferences.getLong("logged_in_user_id", -1L)
    val startDestination = if (loggedInUserId != -1L) {
        // Si hay un ID de usuario, ve directamente a Home
        AppRutas.Home.route
    } else {
        // Si no, empieza por la pantalla de bienvenida
        AppRutas.Welcome.route
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = AppRutas.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = AppRutas.Login.route) {
            // El ViewModel se pasa aquí para manejar la lógica de inicio de sesión
            InisioSeecion(
                navController = navController,
                viewModel = userViewModel, // Pasando el ViewModel
                onLoginSuccess = { userId ->
                    // 1. Guardar la sesión del usuario
                    with(sharedPreferences.edit()) {
                        putLong("logged_in_user_id", userId)
                        apply()
                    }
                    // 2. Navegar a la pantalla de inicio y limpiar la pila de atrás
                    navController.navigate(AppRutas.Home.route) {
                        popUpTo(AppRutas.Welcome.route) { inclusive = true }
                        launchSingleTop = true // Evita múltiples copias de Home
                    }
                }
            )
        }

        composable(route = AppRutas.Registro.route) {
            Registro(navController = navController, viewModel = userViewModel)
        }

        composable(route = AppRutas.Home.route) {
            HomeScreen(navController)
        }

        composable(route = AppRutas.Profile.route) {
            ProfileScreen(
                viewModel = userViewModel,
                onLogout = {
                    // 1. Borrar la sesión guardada
                    with(sharedPreferences.edit()) {
                        remove("logged_in_user_id")
                        apply()
                    }
                    // 2. Navegar de vuelta a la pantalla de bienvenida
                    navController.navigate(AppRutas.Welcome.route) {
                        // Limpia toda la pila de navegación para que el usuario no pueda volver atrás
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() } // Añadido el parámetro onBack
            )
        }

        composable(route = AppRutas.Settings.route) {
            SettingsScreen()
        }

        composable(route = AppRutas.Nosotros.route) {
            NosotrosScreen(navController)
        }

        composable(route = AppRutas.Tienda.route) {
            TiendaScreen(navController = navController, productViewModel = productViewModel)
        }

        composable(route = AppRutas.Cart.route) {
            CartScreen(navController = navController, productViewModel = productViewModel)
        }

        composable(route = AppRutas.Eventos.route) {
            EventosScreen(navController)
        }
    }
}
