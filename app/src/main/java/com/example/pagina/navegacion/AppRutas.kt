package com.example.pagina.navegacion

/**
 * Define todas las rutas de navegación de la aplicación en un solo lugar.
 * Usar una clase sellada (sealed class) asegura que solo las rutas definidas aquí son válidas.
 */
sealed class AppRutas(val route: String) {
    // Cada "object" es una pantalla específica de la app.
    object Welcome: AppRutas("/welcome")
    object Login: AppRutas("/login")
    object Registro: AppRutas("/registro")
    object Home: AppRutas("/home")
    object Profile: AppRutas("/profile")
    object Settings: AppRutas("/settings")
    object Nosotros: AppRutas("/nosotros")
    object Tienda: AppRutas("/tienda")
}
