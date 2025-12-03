package com.example.pagina.navegacion

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pagina.api.viewmodel.PostViewModel
import com.example.pagina.pantallas.AddProductScreen
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
    postViewModel: PostViewModel, // Acepta el PostViewModel
    sharedPreferences: SharedPreferences
) {
    val navController = rememberNavController()

    val loggedInUserId = sharedPreferences.getLong("logged_in_user_id", -1L)
    val startDestination = if (loggedInUserId != -1L) {
        AppRutas.Home.route
    } else {
        AppRutas.Welcome.route
    }

    LaunchedEffect(loggedInUserId) {
        if (loggedInUserId != -1L) {
            userViewModel.loadUserById(loggedInUserId)
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = AppRutas.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = AppRutas.Login.route) {
            InisioSeecion(
                navController = navController,
                viewModel = userViewModel,
                onLoginSuccess = { userId ->
                    with(sharedPreferences.edit()) {
                        putLong("logged_in_user_id", userId)
                        apply()
                    }
                    userViewModel.loadUserById(userId)
                    
                    navController.navigate(AppRutas.Home.route) {
                        popUpTo(AppRutas.Welcome.route) { inclusive = true }
                        launchSingleTop = true
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
                    with(sharedPreferences.edit()) {
                        remove("logged_in_user_id")
                        apply()
                    }
                    navController.navigate(AppRutas.Welcome.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = AppRutas.Settings.route) {
            SettingsScreen()
        }

        composable(route = AppRutas.Nosotros.route) {
            NosotrosScreen(navController)
        }

        composable(route = AppRutas.Tienda.route) {
            TiendaScreen(
                navController = navController, 
                productViewModel = productViewModel, 
                userViewModel = userViewModel,
                postViewModel = postViewModel // Pasa el PostViewModel a la pantalla
            )
        }

        composable(route = AppRutas.Cart.route) {
            CartScreen(navController = navController, productViewModel = productViewModel)
        }

        composable(route = AppRutas.Eventos.route) {
            EventosScreen(navController)
        }

        composable(route = AppRutas.AddProduct.route) {
            AddProductScreen(navController = navController, productViewModel = productViewModel)
        }
    }
}
