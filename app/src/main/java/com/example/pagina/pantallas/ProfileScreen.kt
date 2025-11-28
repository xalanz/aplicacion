package com.example.pagina.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pagina.viewmodel.UserViewModel

// Define los colores del degradado basados en la imagen
val gradientColors = listOf(Color(0xFF6A1BBE), Color(0xFF345C8C)) // Morado oscuro a azul oscuro
 // Azul Violeta a Azul Acero

/**
 * Pantalla de Perfil de Usuario con el estilo visual de la imagen.
 * Muestra los datos del último usuario registrado y permite simular la edición.
 */
@Composable
fun ProfileScreen(viewModel: UserViewModel) {
    // Los datos del usuario actual
    val latestUser by viewModel.latestUser.collectAsState()

    // Estados mutables para simular la edición de los campos (similar a la imagen)
    // Usamos los datos del usuario si existen, si no, usamos valores por defecto.
    val initialUser = latestUser
    var name by remember { mutableStateOf(initialUser?.name ?: "David") }
    var lastName by remember { mutableStateOf(initialUser?.apellidos ?: "Smith") }
    var email by remember { mutableStateOf(initialUser?.email ?: "davidsmith@pro") }
    // Puedes añadir más campos si es necesario (e.g., Dirección)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6FA)) // Fondo muy claro para el resto de la pantalla
    ) {
        // --- Sección Superior con Degradado ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp) // Altura para la sección de encabezado
                .background(Brush.verticalGradient(gradientColors)),
            contentAlignment = Alignment.TopCenter
        ) {
            // Barra superior (Back/Ajustes)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp)
            ) {
                // Icono de flecha hacia atrás
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                )

                // Texto "Profile" (Opcional, la imagen lo omite, pero ayuda a la navegación)
                Text(
                    text = "Perfil de usuario",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

            }

            // Contenido del Perfil (Avatar y Nombre)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 100.dp)
            ) {
                // Avatar (Usamos un Icono con un fondo oscuro para simular el avatar de la imagen)
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E1E2C)), // Fondo oscuro para el avatar
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar del Usuario",
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Nombre
                Text(
                    text = "$name $lastName",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

            }
        }

        // --- Sección de Formularios y Botón (Fondo Blanco/Claro) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1F222A))
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de Nombre
            ProfileTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nombre",
                placeholder = "Nombre"
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Apellido
            ProfileTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = "Apellido",
                placeholder = "Apellido"
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Email
            ProfileTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email ",
                placeholder = "Correo Electrónico"
            )
            // Puedes añadir aquí un Spacer(modifier = Modifier.height(30.dp))

            // Spacer para empujar el botón "Next" hacia abajo (si la pantalla es más grande)
            Spacer(modifier = Modifier.weight(1f))

            // Botón "Next" (Siguiente)
            Button(
                onClick = { /* Lógica para guardar o ir a la siguiente pantalla */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                shape = MaterialTheme.shapes.medium // Borde ligeramente redondeado
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF6A1BBE), // Morado eléctrico
                                    Color(0xFF8A2BE2)  // Violeta del degradado
                                )
                            ),
                            shape = MaterialTheme.shapes.medium
                        ),
                    contentAlignment = Alignment.Center
                ) {
                Text(text = "Actualizar", style = MaterialTheme.typography.titleMedium,color = Color.White)
            }
        }

        }
        }
    }



/**
 * Componente reutilizable para los campos de texto del perfil.
 * Simula el estilo de línea inferior de la imagen.
 */
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Usamos OutlinedTextField o BasicTextField con un Divider para simular la línea
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(placeholder,color = Color.Gray) },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray, // Color del borde cuando está enfocado
                unfocusedBorderColor = Color.LightGray, // Color del borde cuando no está enfocado
                focusedContainerColor = Color.Transparent, // Fondo transparente
                unfocusedContainerColor = Color.Transparent
            )
        )
        }
    }
