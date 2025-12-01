package com.example.pagina.navegacion

/**
 * Define todas las rutas (direcciones) de navegación de la aplicación en un solo lugar.
 *
 * El uso de una `sealed class` (clase sellada) para gestionar las rutas ofrece varias ventajas:
 * 1.  **Seguridad de Tipos (Type Safety)**: Previene errores de escritura al navegar, ya que el compilador
 *     conoce todas las rutas posibles. En lugar de usar un string como "/home", se usa `AppRutas.Home.route`,
 *     lo que evita errores tipográficos.
 * 2.  **Centralización**: Todas las rutas de la aplicación están organizadas en un único archivo,
 *     lo que facilita su mantenimiento y consulta.
 * 3.  **Autocompletado**: El IDE puede autocompletar las rutas disponibles, mejorando la productividad.
 *
 * @param route La cadena de texto que representa la ruta única para la navegación (ej. "/welcome").
 */
sealed class AppRutas(val route: String) {
    /**
     * Cada `object` representa una pantalla única dentro de la aplicación.
     * Al ser objetos, son instancias únicas (singletons), lo que es eficiente en términos de memoria.
     */

    // Pantalla de bienvenida, la primera que ve el usuario.
    object Welcome: AppRutas("/welcome")

    // Pantalla de inicio de sesión.
    object Login: AppRutas("/login")

    // Pantalla de registro de nuevos usuarios.
    object Registro: AppRutas("/registro")

    // Pantalla principal o de inicio después de iniciar sesión.
    object Home: AppRutas("/home")

    // Pantalla de perfil del usuario.
    object Profile: AppRutas("/profile")

    // Pantalla de configuración.
    object Settings: AppRutas("/settings")

    // Pantalla de información "Sobre Nosotros".
    object Nosotros: AppRutas("/nosotros")

    // Pantalla de la tienda.
    object Tienda: AppRutas("/tienda")

    // Pantalla del carrito de compras.
    object Cart: AppRutas("/cart")

    // Pantalla de eventos.
    object Eventos: AppRutas("/eventos")

    // Pantalla para agregar un nuevo producto.
    object AddProduct: AppRutas("/add_product")
}