package com.example.pagina.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagina.data.local.User
import com.example.pagina.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel, modifier: Modifier = Modifier) {
    val users by viewModel.users.collectAsState(initial = emptyList())
    var name by remember { mutableStateOf("") }

    // Estados para el modal de edición
    var showEditDialog by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<User?>(null) }
    var newName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Usuarios", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        // Formulario de agregar usuario
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (name.isNotBlank()) {
                    viewModel.addUser(name, "", "", "", "") // CORREGIDO AQUÍ
                    name = ""
                }
            }) {
                Text("Agregar")
            }
        }
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(users) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(user.name)
                    Row {
                        IconButton(onClick = {
                            userToEdit = user
                            newName = user.name
                            showEditDialog = true
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = { viewModel.deleteUser(user) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
                HorizontalDivider()
            }
        }

        // Modal de edición
        if (showEditDialog && userToEdit != null) {
            AlertDialog(
                onDismissRequest = { showEditDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        userToEdit?.let {
                            viewModel.updateUser(it, newName)
                        }
                        showEditDialog = false
                    }) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEditDialog = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text("Editar usuario") },
                text = {
                    Column {
                        TextField(
                            value = newName,
                            onValueChange = { newName = it },
                            label = { Text("Nombre") },
                            singleLine = true
                        )
                    }
                }
            )
        }
    }
}