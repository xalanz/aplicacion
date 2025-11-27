package com.example.pagina.navegacion

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

/**
 * Gestiona la navegación principal de la aplicación.
 * Es el "controlador de tráfico" que decide qué pantalla mostrar.
 */
@Composable
fun AppNavigation(userViewModel: UserViewModel, productViewModel: ProductViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRutas.Welcome.route) {

        composable(route = AppRutas.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = AppRutas.Login.route) {
            InisioSeecion(navController, onLoginSuccess = {
                navController.navigate(AppRutas.Home.route) {
                    popUpTo(AppRutas.Welcome.route) { inclusive = true }
                }
            })
        }

        composable(route = AppRutas.Registro.route) {
            Registro(navController = navController, viewModel = userViewModel)
        }

        composable(route = AppRutas.Home.route) {
            HomeScreen(navController)
        }

        composable(route = AppRutas.Profile.route) {
            ProfileScreen(viewModel = userViewModel)
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