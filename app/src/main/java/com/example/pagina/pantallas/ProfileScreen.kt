package com.example.pagina.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagina.viewmodel.UserViewModel

/**
 * Pantalla de Perfil de Usuario.
 * Muestra los datos del último usuario registrado.
 */
@Composable
fun ProfileScreen(viewModel: UserViewModel) {
    // "collectAsState" observa el Flow del ViewModel y redibuja la pantalla automáticamente
    // cada vez que los datos del último usuario cambian.
    val latestUser by viewModel.latestUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Comprueba si existe un usuario. Si no, muestra un mensaje.
        val user = latestUser
        if (user != null) {
            Text("Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Nombre: ${user.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Apellidos: ${user.apellidos}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Email: ${user.email}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Dirección: ${user.direccion}")
        } else {
            Text("Aún no hay ningún usuario registrado.", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
