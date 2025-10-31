package com.example.pagina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pagina.navegacion.AppNavigation
import com.example.pagina.ui.theme.PaginaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaginaTheme {
                AppNavigation()
            }
        }
    }
}