package com.example.pagina.registro_inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pagina.ui.theme.PaginaTheme
import com.example.pagina.viewmodel.UserViewModel

@Composable
fun Registro(navController: NavController, viewModel: UserViewModel, modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verifyPassword by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    RegistroForm(
        modifier = modifier,
        nombre = nombre,
        apellidos = apellidos,
        correo = correo,
        password = password,
        verifyPassword = verifyPassword,
        direccion = direccion,
        errorMessage = errorMessage,
        onNombreChange = { nombre = it; errorMessage = null },
        onApellidosChange = { apellidos = it; errorMessage = null },
        onCorreoChange = { correo = it; errorMessage = null },
        onPasswordChange = { password = it; errorMessage = null },
        onVerifyPasswordChange = { verifyPassword = it; errorMessage = null },
        onDireccionChange = { direccion = it; errorMessage = null },
        onRegisterClick = {
            when {
                nombre.isBlank() || apellidos.isBlank() || correo.isBlank() || password.isBlank() || direccion.isBlank() -> {
                    errorMessage = "Todos los campos son obligatorios"
                }
                !correo.endsWith("@gmail.com", ignoreCase = true) -> {
                    errorMessage = "El correo debe ser @gmail.com"
                }
                password.length < 6 -> {
                    errorMessage = "La contraseña debe tener al menos 6 caracteres"
                }
                password != verifyPassword -> {
                    errorMessage = "Las contraseñas no coinciden"
                }
                else -> {
                    viewModel.addUser(nombre, apellidos, correo, password, direccion)
                    navController.popBackStack()
                }
            }
        }
    )
}

@Composable
fun RegistroForm(
    modifier: Modifier = Modifier,
    nombre: String,
    apellidos: String,
    correo: String,
    password: String,
    verifyPassword: String,
    direccion: String,
    errorMessage: String?,
    onNombreChange: (String) -> Unit,
    onApellidosChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPasswordChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0B1F))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Formulario de Registro", color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre, onValueChange = onNombreChange, label = { Text("Nombre", color = Color.White) },
            singleLine = true, textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = campoTextoColores()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellidos, onValueChange = onApellidosChange, label = { Text("Apellido", color = Color.White) },
            singleLine = true, textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = campoTextoColores()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo, onValueChange = onCorreoChange, label = { Text("Correo electrónico", color = Color.White) },
            singleLine = true, textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = campoTextoColores()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password, onValueChange = onPasswordChange, label = { Text("Contraseña", color = Color.White) },
            singleLine = true, visualTransformation = PasswordVisualTransformation(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White), colors = campoTextoColores()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = verifyPassword, onValueChange = onVerifyPasswordChange, label = { Text("Verificar contraseña", color = Color.White) },
            singleLine = true, visualTransformation = PasswordVisualTransformation(),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White), colors = campoTextoColores()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = direccion, onValueChange = onDireccionChange, label = { Text("Dirección", color = Color.White) },
            singleLine = true, textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            colors = campoTextoColores()
        )

        errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .width(220.dp)
                .height(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF7B2FF7), Color(0xFF9C4DFF), Color(0xFFC084FC))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Button(
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White),
                elevation = null
            ) {
                Text("Registrarse")
            }
        }
    }
}

@Composable
private fun campoTextoColores() = TextFieldDefaults.colors(
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    cursorColor = Color.White,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent
)

@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    PaginaTheme {
        RegistroForm(
            nombre = "", apellidos = "", correo = "", password = "",
            verifyPassword = "", direccion = "", errorMessage = "Esto es un error de prueba",
            onNombreChange = {}, onApellidosChange = {}, onCorreoChange = {},
            onPasswordChange = {}, onVerifyPasswordChange = {}, onDireccionChange = {},
            onRegisterClick = {}
        )
    }
}
