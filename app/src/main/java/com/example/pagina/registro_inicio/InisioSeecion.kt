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
import com.example.pagina.navegacion.AppRutas
import com.example.pagina.ui.theme.PaginaTheme

/**
 * Pantalla de Inicio de Sesión.
 * Permite a los usuarios existentes acceder a la aplicación.
 */
@Composable
fun InisioSeecion(navController: NavController, onLoginSuccess: () -> Unit) {

    // "remember" guarda el estado (el texto) del campo de correo electrónico aunque la pantalla se redibuje.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Inicio de Sesión")

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el correo.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para la contraseña, con la visibilidad oculta.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón que se activa cuando el usuario intenta iniciar sesión.
        Button(onClick = { 
            // En una app real, aquí se verificarían las credenciales con la base de datos.
            // Si es exitoso, se llama a onLoginSuccess para navegar a la pantalla principal.
            onLoginSuccess()
        }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para redirigir a los nuevos usuarios a la pantalla de registro.
        Button(onClick = {
            navController.navigate(AppRutas.Registro.route)
        }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InisioSeecionPreview() {
    PaginaTheme {
        InisioSeecion(rememberNavController(), onLoginSuccess = {})
    }
}
