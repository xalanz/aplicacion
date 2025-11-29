package com.example.pagina.pantallas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pagina.viewmodel.UserViewModel

// Define los colores del degradado basados en la imagen
val gradientColors = listOf(Color(0xFF6A1BBE), Color(0xFF345C8C)) // Morado oscuro a azul oscuro

/**
 * Pantalla de Perfil de Usuario con el estilo visual de la imagen.
 * Muestra los datos del último usuario registrado y permite simular la edición.
 */
@Composable
fun ProfileScreen(viewModel: UserViewModel) {
    // Los datos del usuario actual
    val latestUser by viewModel.latestUser.collectAsState()

    // Estado para la URI de la imagen de perfil
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher para seleccionar una imagen de la galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val initialUser = latestUser
    var name by remember { mutableStateOf(initialUser?.name ?: "David") }
    var lastName by remember { mutableStateOf(initialUser?.apellidos ?: "Smith") }
    var email by remember { mutableStateOf(initialUser?.email ?: "davidsmith@pro") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F222A)) // Changed background for consistency
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(Brush.verticalGradient(gradientColors)),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = "Perfil de usuario",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 100.dp)
            ) {
                // Avatar con funcionalidad de clic para cambiar la imagen
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E1E2C))
                        .clickable { galleryLauncher.launch("image/*") }, // Abre la galería
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = "Avatar del Usuario",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar del Usuario",
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    // Icono de cámara superpuesto
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "Cambiar Foto",
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(30.dp)
                            .padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$name $lastName",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nombre",
                placeholder = "Nombre"
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = "Apellido",
                placeholder = "Apellido"
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email ",
                placeholder = "Correo Electrónico"
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Lógica para guardar o ir a la siguiente pantalla */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF6A1BBE),
                                    Color(0xFF8A2BE2)
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
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(placeholder,color = Color.Gray) },
            shape = RoundedCornerShape(8.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF8A2BE2),
                unfocusedBorderColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}
