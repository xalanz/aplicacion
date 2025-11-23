package com.example.pagina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.pagina.data.local.AppDatabase
import com.example.pagina.data.repository.ProductRepository
import com.example.pagina.data.repository.UserRepository
import com.example.pagina.navegacion.AppNavigation
import com.example.pagina.ui.theme.PaginaTheme
import com.example.pagina.viewmodel.ProductViewModel
import com.example.pagina.viewmodel.ProductViewModelFactory
import com.example.pagina.viewmodel.UserViewModel
import com.example.pagina.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my_database"
        ).fallbackToDestructiveMigration()
            .build()

        // --- Repositorios ---
        val userRepo = UserRepository(db.userDao())
        val productRepo = ProductRepository(db.productDao())

        // --- Fábricas de ViewModels ---
        val userViewModelFactory = UserViewModelFactory(userRepo)
        val productViewModelFactory = ProductViewModelFactory(productRepo)

        setContent {
            PaginaTheme {
                // --- Instancias de ViewModels ---
                val userViewModel: UserViewModel = viewModel(factory = userViewModelFactory)
                val productViewModel: ProductViewModel = viewModel(factory = productViewModelFactory)

                // --- Navegación Principal ---
                // Pasamos ambos ViewModels a nuestro sistema de navegación.
                AppNavigation(userViewModel = userViewModel, productViewModel = productViewModel)
            }
        }
    }
}