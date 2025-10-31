package com.example.pagina.navegacion

/**
 * Define todas las rutas de navegación de la aplicación en un solo lugar.
 * Usar una `sealed class` (clase sellada) para las rutas es una buena práctica porque:
 * 1. Evita errores de escritura: En lugar de usar Strings ("/login"), usamos objetos (AppRorutas.Login).
 * 2. Autocompletado: El IDE te sugerirá las rutas disponibles.
 * 3. Seguridad de tipos: El compilador conoce todas las rutas posibles, lo que ayuda a evitar errores.
 *
 * @param route La ruta (String) asociada a cada pantalla, que se usará en el NavHost.
 */
sealed class AppRorutas(val route: String) {

    /**
     * Representa la pantalla de bienvenida.
     * Es un `object` porque es una pantalla simple que no necesita recibir parámetros.
     * La ruta de acceso es "/welcome".
     */
    object Welcome: AppRorutas(route = "/welcome")

    /**
     * Representa la pantalla de inicio de sesión (Login).
     * Es un `object` porque no necesita parámetros.
     * La ruta de acceso es "/Login".
     */
    object Login: AppRorutas(route = "/Login")

    /**
     * Representa la pantalla de registro.
     * Es un `object` porque no necesita parámetros.
     * La ruta de acceso es "/registro".
     */
    object Registro: AppRorutas(route = "/registro")

    /**
     * Representa una pantalla de detalle que necesita un parámetro para saber qué mostrar (p. ej., un producto o un perfil).
     * Es una `data class` porque debe recibir un argumento (`itemId`) para poder construir la ruta.
     * La ruta base es "detail/{itemId}", donde `{itemId}` es un marcador de posición.
     *
     * @param itemId El identificador único del elemento que se quiere mostrar.
     */
    data class Detail (val itemId:String): AppRorutas(route = "detail/{itemId}")
    {
        /**
         * Construye la ruta de navegación completa, reemplazando el marcador de posición `{itemId}`
         * con el valor real que se le ha pasado.
         * Ejemplo de uso:
         * `navController.navigate(AppRorutas.Detail(itemId = "123").buildRoute())`
         * Esto navegará a la ruta final "detail/123".
         */
        fun buildRoute():String{
            return route.replace("{itemId}",itemId)
        }
    }
}