package com.example.pagina.navegacion

sealed class AppRutas(val route: String) {
    object Welcome: AppRutas("/welcome")
    object Login: AppRutas("/login")
    object Registro: AppRutas("/registro")
    object Home: AppRutas("/home")
    object Profile: AppRutas("/profile")
    object Settings: AppRutas("/settings")
    object Nosotros: AppRutas("/nosotros")
    object Tienda: AppRutas("/tienda")
}
