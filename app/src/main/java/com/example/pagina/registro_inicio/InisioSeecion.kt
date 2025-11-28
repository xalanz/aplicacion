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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.unit.dp




/**
 * Pantalla de Inicio de Sesión.
 * Permite a los usuarios existentes acceder a la aplicación.
 */
@Composable
fun InisioSeecion(navController: NavController, onLoginSuccess: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val fondo = Color(0xFF0A0B1F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Inicio de Sesión",
                color = Color.White,
                fontSize = 28.sp    // Puedes cambiar 28 por 30, 32, etc.
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
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
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña" , color = Color.White)},
                visualTransformation = PasswordVisualTransformation(),
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

            Button(
                onClick = { onLoginSuccess() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent   // <-- AQUÍ CAMBIA EL COLOR BASE DEL BOTÓN
                ),
                contentPadding = PaddingValues(),       // <-- NECESARIO PARA QUE EL degradado cubra todo
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF9D4EDD), // <-- Morado eléctrico brillante
                                    Color(0xFF7B2CBF)  // <-- Morado profundo
                                )
                            ),
                            RoundedCornerShape(12.dp)     // <-- Bordes redondeados
                        )
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        color = Color.White
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    navController.navigate(AppRutas.Registro.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent   // <-- AQUÍ TAMBIÉN
                ),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF9D4EDD), // <-- Morado eléctrico brillante
                                    Color(0xFF7B2CBF)  // <-- Morado profundo
                                )
                            ),
                            RoundedCornerShape(12.dp)
                        )
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "¿No tienes cuenta? Regístrate",
                        color = Color.White
                    )
                }
            }


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
