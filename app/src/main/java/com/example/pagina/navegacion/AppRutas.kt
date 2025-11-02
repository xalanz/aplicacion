package com.example.pagina.navegacion

sealed class AppRutas(val route: String) {
    object Welcome: AppRutas(route = "/welcome")
    object Login: AppRutas(route = "/login")
    object Registro: AppRutas(route = "/registro")
    object Home: AppRutas(route = "/home")
    object Profile: AppRutas(route = "/profile")
    object Settings: AppRutas(route = "/settings")
    object Nosotros: AppRutas(route = "/nosotros")
    object Tienda: AppRutas(route = "/tienda")
}
