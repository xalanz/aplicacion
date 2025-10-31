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
 * Define la pantalla de inicio de sesión de la aplicación.
 * Es una función @Composable, lo que significa que describe una parte de la interfaz de usuario.
 *
 * @param navController El controlador que se usa para navegar entre las diferentes pantallas.
 */
@Composable
fun InicioSesionScreen(navController: NavController) {

    // --- 1. ESTADO DEL FORMULARIO ---
    // `remember` y `mutableStateOf` se usan para crear y recordar el estado de los campos de texto.
    // `email` y `password` son variables de estado. Cuando su valor cambia (porque el usuario escribe),
    // la pantalla se "recompone" (se redibuja) automáticamente para mostrar el nuevo texto.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // --- 2. DISEÑO DE LA PANTALLA ---
    // `Column` organiza a sus componentes hijos en una secuencia vertical (uno debajo del otro).
    Column(
        modifier = Modifier
            .fillMaxSize()      // Hace que la columna ocupe todo el espacio disponible.
            .padding(16.dp),   // Añade un margen de 16.dp en todos los lados.
        verticalArrangement = Arrangement.Center,   // Centra los elementos verticalmente en la pantalla.
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente.
    ) {

        Text("Inicio de Sesión") // Un texto simple que sirve como título.

        // `Spacer` crea un espacio vacío para separar elementos.
        Spacer(modifier = Modifier.height(16.dp))

        // --- CAMPO DE TEXTO PARA EL CORREO ---
        OutlinedTextField(
            value = email, // El texto que se muestra en el campo (vinculado a la variable de estado).
            onValueChange = { email = it }, // Se ejecuta cada vez que el usuario escribe, actualizando la variable `email`.
            label = { Text("Correo Electrónico") } // La etiqueta que se muestra sobre el campo.
        )
        Spacer(modifier = Modifier.height(8.dp))

        // --- CAMPO DE TEXTO PARA LA CONTRASEÑA ---
        OutlinedTextField(
            value = password, // Vinculado a la variable de estado `password`.
            onValueChange = { password = it }, // Actualiza la variable `password` al escribir.
            label = { Text("Contraseña") },
            // `visualTransformation` se usa para cambiar la apariencia del texto.
            // `PasswordVisualTransformation()` reemplaza los caracteres por puntos (••••) para ocultar la contraseña.
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- BOTÓN DE INICIO DE SESIÓN ---
        Button(onClick = { 
            // `onClick` define la acción que se ejecuta cuando el usuario pulsa el botón.
            // TODO: Aquí es donde se debe añadir la lógica para verificar el correo y la contraseña,
            // por ejemplo, usando Firebase Authentication.
        }) {
            Text("Iniciar Sesión") // El texto que se muestra dentro del botón.
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- BOTÓN PARA IR A LA PANTALLA DE REGISTRO ---
        Button(onClick = {
            // Usamos el `navController` para navegar a la pantalla de registro.
            // `AppRorutas.Registro.route` contiene la ruta de destino (p. ej., "/registro").
            navController.navigate(AppRorutas.Registro.route)
        }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

/**
 * Proporciona una vista previa de la pantalla de inicio de sesión en el editor de Android Studio.
 * La anotación `@Preview` permite visualizar el Composable sin necesidad de ejecutar la aplicación.
 * Esto es muy útil para agilizar el diseño de la interfaz.
 */
@Preview(showBackground = true)
@Composable
fun InicioSesionScreenPreview() {
    // PaginaTheme envuelve la vista previa para aplicarle el tema de la aplicación (colores, fuentes, etc.).
    PaginaTheme {
        // Se crea un NavController de prueba con `rememberNavController()`,
        // ya que la vista previa no tiene un contexto de navegación real.
        InicioSesionScreen(rememberNavController())
    }
}