package com.example.pagina.navegacion

sealed class AppRutas(val route: String) {
    object Welcome: AppRutas(route = "/welcome")
    object Login: AppRutas(route = "/Login")
    object Registro: AppRutas(route = "/registro")
    data class Detail (val itemId:String): AppRutas(route = "detail/{itemId}")
    {
        fun buildRoute():String{
            return route.replace("{itemId}",itemId)
        }
    }
}
