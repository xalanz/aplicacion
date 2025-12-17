package com.example.pagina.registro_inicio

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pagina.viewmodel.UserViewModel

@Composable
fun Registro(navController: NavController, viewModel: UserViewModel, modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf<String?>(null) }
    var apellidosError by remember { mutableStateOf<String?>(null) }
    var correoError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var direccionError by remember { mutableStateOf<String?>(null) }
    var termsError by remember { mutableStateOf(false) }

    fun validate(): Boolean {
        nombreError = if (nombre.length in 4..10) null else "Por favor ingrese un nombre entre 4 y 10 caracteres"
        apellidosError = if (apellidos.isNotBlank()) null else "Por favor ingrese su apellido"
        correoError = if (Patterns.EMAIL_ADDRESS.matcher(correo).matches()) null else "Correo invalido"
        passwordError = if (password.length in 4..8) null else "Ingrese una contraseña entre 4 y 8 caracteres"
        direccionError = if (direccion.isNotBlank()) null else "Por favor ingrese una direccion"
        termsError = !acceptedTerms

        val isValid = nombreError == null && apellidosError == null && correoError == null && passwordError == null && direccionError == null && !termsError
        if (isValid) {
            viewModel.addUser(nombre, apellidos, correo, password, direccion)
            navController.popBackStack()
        }
        return isValid
    }

    RegistroForm(
        modifier = modifier,
        nombre = nombre,
        apellidos = apellidos,
        correo = correo,
        password = password,
        direccion = direccion,
        acceptedTerms = acceptedTerms,
        nombreError = nombreError,
        apellidosError = apellidosError,
        correoError = correoError,
        passwordError = passwordError,
        direccionError = direccionError,
        termsError = termsError,
        onNombreChange = { nombre = it; nombreError = null },
        onApellidosChange = { apellidos = it; apellidosError = null },
        onCorreoChange = { correo = it; correoError = null },
        onPasswordChange = { password = it; passwordError = null },
        onDireccionChange = { direccion = it; direccionError = null },
        onTermsChange = { acceptedTerms = it; termsError = false },
        onRegisterClick = { validate() }
    )
}

@Composable
private fun RegistroForm(
    modifier: Modifier = Modifier,
    nombre: String,
    apellidos: String,
    correo: String,
    password: String,
    direccion: String,
    acceptedTerms: Boolean,
    nombreError: String?,
    apellidosError: String?,
    correoError: String?,
    passwordError: String?,
    direccionError: String?,
    termsError: Boolean,
    onNombreChange: (String) -> Unit,
    onApellidosChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onTermsChange: (Boolean) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0B1F)) // Dark blue background
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Cuenta", fontSize = 28.sp, color = Color.White)
        Text("Rellena los datos para unirte", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 32.dp))

        ValidationTextField(
            value = nombre,
            onValueChange = onNombreChange,
            placeholder = "Nombre",
            isError = nombreError != null,
            errorMessage = nombreError,
            keyboardType = KeyboardType.Text
        )

        ValidationTextField(
            value = apellidos,
            onValueChange = onApellidosChange,
            placeholder = "Apellido",
            isError = apellidosError != null,
            errorMessage = apellidosError,
            keyboardType = KeyboardType.Text
        )

        ValidationTextField(
            value = correo,
            onValueChange = onCorreoChange,
            placeholder = "Correo electrónico",
            isError = correoError != null,
            errorMessage = correoError,
            keyboardType = KeyboardType.Email
        )

        ValidationTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Contraseña",
            isError = passwordError != null,
            errorMessage = passwordError,
            keyboardType = KeyboardType.Password
        )

        ValidationTextField(
            value = direccion,
            onValueChange = onDireccionChange,
            placeholder = "Dirección",
            isError = direccionError != null,
            errorMessage = direccionError,
            keyboardType = KeyboardType.Text
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Checkbox(
                checked = acceptedTerms,
                onCheckedChange = onTermsChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF7B2FF7), // Purple color
                    uncheckedColor = if (termsError) MaterialTheme.colorScheme.error else Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = "Acepto los términos y condiciones",
                color = if (termsError) MaterialTheme.colorScheme.error else Color.White,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B2FF7)) // Purple color
        ) {
            Text("Crear Cuenta", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
private fun ValidationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
    errorMessage: String?,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().padding(bottom = if (isError) 0.dp else 8.dp),
        placeholder = { Text(placeholder, color = Color.Gray) },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isError) MaterialTheme.colorScheme.error else Color.Gray,
            unfocusedBorderColor = if (isError) MaterialTheme.colorScheme.error else Color.Gray.copy(alpha = 0.5f),
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        )
    )
}
