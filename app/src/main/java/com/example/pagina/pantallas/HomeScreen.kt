package com.example.pagina.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pagina.navegacion.AppRutas
import kotlinx.coroutines.launch

/**
 * Pantalla principal (Home) con una barra de aplicación superior y un menú lateral de navegación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    // Estado para controlar si el menú lateral está abierto o cerrado.
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // Scope para poder abrir y cerrar el menú con una corrutina.
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Contenido del menú lateral.
            ModalDrawerSheet {
                Text("Menú", modifier = Modifier.padding(16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                NavigationDrawerItem(
                    label = { Text("Ir a Perfil") },
                    selected = false,
                    onClick = { 
                        scope.launch { drawerState.close() } // Cierra el menú.
                        navController.navigate(AppRutas.Profile.route) // Navega a perfil.
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Nosotros") },
                    selected = false,
                    onClick = { 
                        scope.launch { drawerState.close() }
                        navController.navigate(AppRutas.Nosotros.route)
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Tienda") },
                    selected = false,
                    onClick = { 
                        scope.launch { drawerState.close() }
                        navController.navigate(AppRutas.Tienda.route)
                    }
                )
            }
        }
    ) { 
        // Contenido principal de la pantalla.
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pantalla de Inicio") },
                    navigationIcon = {
                        // Icono de menú (hamburguesa) que abre el menú lateral.
                        IconButton(onClick = { 
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            // El contenido que va dentro de la pantalla principal.
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text("Bienvenido a la pantalla principal.")
                Spacer(modifier = Modifier.height(16.dp))
                // Botón para navegar a la pantalla de la Tienda.
                Button(onClick = { navController.navigate(AppRutas.Tienda.route) }) {
                    Text("Ir a la Tienda")
                }
            }
        }
    }
}
