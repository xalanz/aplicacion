package com.example.pagina.navegacion

sealed class AppRorutas(val route: String) {

    data object Registro: AppRorutas(route = "/registro")

    data class Detail (val itemId:String): AppRorutas(route = "detail/{itemId}")
    {
        fun buildRoute():String{
            return route.replace("{itemId}",itemId)
        }
    }
}