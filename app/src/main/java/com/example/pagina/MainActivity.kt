package com.example.pagina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pagina.navegacion.AppRorutas
import com.example.pagina.registro_inicio.RegistroScreen
import com.example.pagina.ui.theme.PaginaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaginaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { 
                        LoginScreen(navController = navController)
                    }
                    composable(AppRorutas.Registro.route) {
                        RegistroScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { /*TODO*/ }) {
            Text("Inicio de sesión")
        }
        Button(onClick = { navController.navigate(AppRorutas.Registro.route) }) {
            Text("Registro")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PaginaTheme {
        LoginScreen(rememberNavController())
    }
}