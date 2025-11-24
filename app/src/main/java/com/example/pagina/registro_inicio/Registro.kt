package com.example.pagina.registro_inicio
// FORCE RE-COMPILE

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
import com.example.pagina.ui.theme.PaginaTheme
import com.example.pagina.viewmodel.UserViewModel
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.layout.width












/**
 * Pantalla de Registro de nuevos usuarios.
 * Contiene la lógica principal y el estado de la pantalla.
 */
@Composable
fun Registro(navController: NavController, viewModel: UserViewModel, modifier: Modifier = Modifier) {
    // Estados para cada campo de texto del formulario.
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verifyPassword by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    // Valida que todos los campos estén rellenos para activar el botón de registro.
    val isFormValid = nombre.isNotBlank() && apellidos.isNotBlank() && correo.isNotBlank() && password.isNotBlank() && verifyPassword.isNotBlank() && direccion.isNotBlank()

    // Llama al Composable del formulario, pasándole los estados y los eventos.
    RegistroForm(
        modifier = modifier,
        nombre = nombre,
        apellidos = apellidos,
        correo = correo,
        password = password,
        verifyPassword = verifyPassword,
        direccion = direccion,
        onNombreChange = { nombre = it },
        onApellidosChange = { apellidos = it },
        onCorreoChange = { correo = it },
        onPasswordChange = { password = it },
        onVerifyPasswordChange = { verifyPassword = it },
        onDireccionChange = { direccion = it },
        onRegisterClick = {
            viewModel.addUser(nombre, apellidos, correo, password, direccion)
            navController.popBackStack()
        },
        isFormValid = isFormValid
    )
}

/**
 * Composable que representa únicamente el formulario de registro.
 * No tiene lógica, solo se encarga de mostrar los campos y el botón.
 */
@Composable
fun RegistroForm(
    modifier: Modifier = Modifier,
    nombre: String,
    apellidos: String,
    correo: String,
    password: String,
    verifyPassword: String,
    direccion: String,
    onNombreChange: (String) -> Unit,
    onApellidosChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPasswordChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    isFormValid: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0B1F))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    Text("Formulario de Registro",
        color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre", color = Color.White) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,   // 🔹 Fondo transparente
                unfocusedContainerColor = Color.Transparent, // 🔹 Fondo transparente
                disabledContainerColor = Color.Transparent,  // 🔹 Fondo transparente
                errorContainerColor = Color.Transparent      // 🔹 Fondo transparente
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = apellidos,
            onValueChange = onApellidosChange,
            label = { Text("Apellido", color = Color.White) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,   // 🔹 Fondo transparente
                unfocusedContainerColor = Color.Transparent, // 🔹 Fondo transparente
                disabledContainerColor = Color.Transparent,  // 🔹 Fondo transparente
                errorContainerColor = Color.Transparent      // 🔹 Fondo transparente
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = { Text("Correo electronico", color = Color.White) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,   // 🔹 Fondo transparente
                unfocusedContainerColor = Color.Transparent, // 🔹 Fondo transparente
                disabledContainerColor = Color.Transparent,  // 🔹 Fondo transparente
                errorContainerColor = Color.Transparent      // 🔹 Fondo transparente
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,   // 🔹 Fondo transparente
                unfocusedContainerColor = Color.Transparent, // 🔹 Fondo transparente
                disabledContainerColor = Color.Transparent,  // 🔹 Fondo transparente
                errorContainerColor = Color.Transparent      // 🔹 Fondo transparente
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = verifyPassword,
            onValueChange = onVerifyPasswordChange,
            label = { Text("Verificar contraseña", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,   // 🔹 Fondo transparente
                unfocusedContainerColor = Color.Transparent, // 🔹 Fondo transparente
                disabledContainerColor = Color.Transparent,  // 🔹 Fondo transparente
                errorContainerColor = Color.Transparent      // 🔹 Fondo transparente
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = direccion,
            onValueChange = onDireccionChange,
            label = { Text("Direccion", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,   // 🔹 Fondo transparente
                unfocusedContainerColor = Color.Transparent, // 🔹 Fondo transparente
                disabledContainerColor = Color.Transparent,  // 🔹 Fondo transparente
                errorContainerColor = Color.Transparent      // 🔹 Fondo transparente
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .width(220.dp)                    // 🔹 Ancho reducido
                .height(48.dp)                    // 🔹 Alto reducido
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF7B2FF7),    // Morado eléctrico
                            Color(0xFF9C4DFF),    // Morado brillante
                            Color(0xFFC084FC)     // Lila luminoso
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,  // Fondo transparente (deja ver el degradado)
                    contentColor = Color.White           // Texto blanco
                ),
                elevation = null
            ) {
                Text("Registrarse")
            }
        }

    }


    }


@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    PaginaTheme {
        // La vista previa ahora puede mostrar el formulario, pero sin lógica.
        RegistroForm(
            nombre = "", apellidos = "", correo = "", password = "", 
            verifyPassword = "", direccion = "", onNombreChange = {}, 
            onApellidosChange = {}, onCorreoChange = {}, onPasswordChange = {}, 
            onVerifyPasswordChange = {}, onDireccionChange = {}, 
            onRegisterClick = {}, isFormValid = false
        )
    }
}
