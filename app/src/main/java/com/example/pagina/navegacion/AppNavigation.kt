package com.example.pagina.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pagina.registro_inicio.InicioSesionScreen
import com.example.pagina.registro_inicio.RegistroScreen
import com.example.pagina.registro_inicio.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRutas.Welcome.route) {
        
        composable(route = AppRutas.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = AppRutas.Login.route) {
            InicioSesionScreen(navController)
        }

        composable(route = AppRutas.Registro.route) {
            RegistroScreen(navController = navController, modifier = Modifier)
        }
    }
}
