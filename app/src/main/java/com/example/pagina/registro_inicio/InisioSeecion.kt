package com.example.pagina.registro_inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pagina.navegacion.AppRorutas
import com.example.pagina.ui.theme.PaginaTheme

/**
 * Define la pantalla de inicio de sesión.
 *
 * @param navController El controlador para gestionar la navegación.
 */
@Composable
fun InicioSesionScreen(navController: NavController) {

    // --- ESTADO DEL FORMULARIO ---
    // `remember` y `mutableStateOf` para guardar el estado del correo y la contraseña.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // --- DISEÑO DE LA PANTALLA ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Inicio de Sesión")
        Spacer(modifier = Modifier.height(16.dp))

        // --- CAMPO DE CORREO ---
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // --- CAMPO DE CONTRASEÑA ---
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation() // Oculta el texto de la contraseña.
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- BOTÓN DE INICIO DE SESIÓN ---
        Button(onClick = { /* TODO: Implementar la lógica de autenticación */ }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- BOTÓN PARA IR AL REGISTRO ---
        Button(onClick = {
            // Navega a la pantalla de registro usando la ruta definida en AppRorutas.
            navController.navigate(AppRorutas.Registro.route)
        }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

/**
 * Vista previa para la pantalla de inicio de sesión.
 */
@Preview(showBackground = true)
@Composable
fun InicioSesionScreenPreview() {
    PaginaTheme {
        InicioSesionScreen(rememberNavController())
    }
}