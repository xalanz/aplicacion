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
import androidx.compose.material.icons.filled.Logout
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

val gradientColors = listOf(Color(0xFF6A1BBE), Color(0xFF345C8C))

@Composable
fun ProfileScreen(
    viewModel: UserViewModel,
    onLogout: () -> Unit
) {
    // NOTA: Para que esto funcione, necesitamos el ID del usuario que ha iniciado sesión.
    // Asumiremos que el ViewModel lo gestionará internamente por ahora.
    val currentUser by viewModel.latestUser.collectAsState() // Esto deberá cambiar por el usuario actual

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val name = currentUser?.name ?: ""
    val lastName = currentUser?.apellidos ?: ""
    val email = currentUser?.email ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F222A))
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
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E1E2C))
                        .clickable { galleryLauncher.launch("image/*") },
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
            ProfileInfoField(label = "Nombre", value = name)
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoField(label = "Apellido", value = lastName)
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoField(label = "Email", value = email)
            Spacer(modifier = Modifier.weight(1f))

            // Botón de Cerrar Sesión
            Button(
                onClick = onLogout,
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
                                colors = listOf(Color(0xFFE63946), Color(0xFFD90429)) // Rojo para logout
                            ),
                            shape = MaterialTheme.shapes.medium
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Cerrar Sesión", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    }
                }
            }
        }
    }
}

// Campo de texto de solo lectura para mostrar información
@Composable
fun ProfileInfoField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledTextColor = Color.White,
                disabledBorderColor = Color.Gray
            )
        )
    }
}
