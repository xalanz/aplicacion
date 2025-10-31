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
import com.example.pagina.ui.theme.PaginaTheme

/**
 * Define la pantalla de registro de la aplicación.
 * Es una función @Composable, lo que significa que describe una parte de la interfaz de usuario.
 * @param navController El controlador que gestiona la navegación entre pantallas.
 * @param modifier Un modificador que se puede usar para alterar la apariencia o el comportamiento del Composable.
 */
@Composable
fun RegistroScreen(navController: NavController, modifier: Modifier = Modifier) {

    // --- ESTADO DEL FORMULARIO ---
    // Se utiliza `remember` y `mutableStateOf` para crear y recordar el estado de cada campo de texto.
    // `remember` asegura que el estado no se pierda si la función se recompone (se redibuja).
    // `mutableStateOf` crea un estado observable que, al cambiar, provoca una recomposición de la UI.
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verifyPassword by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    // --- DISEÑO DE LA PANTALLA ---
    // `Column` es un Composable que organiza a sus hijos en una secuencia vertical.
    Column(
        modifier = modifier
            .fillMaxSize() // Ocupa todo el espacio disponible en la pantalla.
            .padding(16.dp), // Añade un relleno de 16 dp en todos los lados.
        verticalArrangement = Arrangement.Center, // Centra a los hijos verticalmente.
        horizontalAlignment = Alignment.CenterHorizontally // Centra a los hijos horizontalmente.
    ) {
        // Título de la pantalla.
        Text("Formulario de Registro")

        // `Spacer` se usa para crear un espacio vacío entre los elementos.
        Spacer(modifier = Modifier.height(16.dp))

        // --- CAMPOS DE TEXTO ---
        // `OutlinedTextField` es un campo de texto con un borde.
        OutlinedTextField(
            value = nombre, // El valor actual del campo de texto (vinculado al estado).
            onValueChange = { nombre = it }, // Se ejecuta cada vez que el usuario escribe, actualizando el estado.
            label = { Text("Nombre") } // La etiqueta que se muestra sobre el campo.
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para la contraseña.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            // `visualTransformation` se usa para ocultar el texto de la contraseña (muestra ••••).
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = verifyPassword,
            onValueChange = { verifyPassword = it },
            label = { Text("Verificar contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- BOTÓN DE REGISTRO ---
        Button(onClick = { 
            // Aquí se define la acción que se ejecuta al pulsar el botón.
            // `navController.popBackStack()` navega a la pantalla anterior en la pila de navegación.
            // En este caso, regresa a la pantalla de inicio de sesión.
            navController.popBackStack() 
        }) {
            Text("Registrarse")
        }
    }
}

/**
 * Proporciona una vista previa de la pantalla de registro en el editor de Android Studio.
 * La anotación `@Preview` permite visualizar el Composable sin necesidad de ejecutar la aplicación.
 */
@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    PaginaTheme {
        // Se usa `rememberNavController()` para crear un NavController falso,
        // ya que la vista previa no tiene un contexto de navegación real.
        RegistroScreen(rememberNavController())
    }
}
