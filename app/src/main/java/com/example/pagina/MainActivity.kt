package com.example.pagina

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.pagina.api.repository.PostRepository
import com.example.pagina.api.viewmodel.PostViewModel
import com.example.pagina.api.viewmodel.PostViewModelFactory
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

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my_database"
        ).fallbackToDestructiveMigration()
            .build()

        // --- Repositorios ---
        val userRepo = UserRepository(db.userDao())
        val productRepo = ProductRepository(db.productDao())
        val postRepo = PostRepository() // Repositorio para la API

        // --- Fábricas de ViewModels ---
        val userViewModelFactory = UserViewModelFactory(userRepo)
        val productViewModelFactory = ProductViewModelFactory(productRepo)
        val postViewModelFactory = PostViewModelFactory(postRepo) // Fábrica para PostViewModel

        // --- Manejo de Sesión ---
        val sharedPreferences = getSharedPreferences("app_session", Context.MODE_PRIVATE)

        setContent {
            PaginaTheme {
                // --- Instancias de ViewModels ---
                val userViewModel: UserViewModel = viewModel(factory = userViewModelFactory)
                val productViewModel: ProductViewModel = viewModel(factory = productViewModelFactory)
                val postViewModel: PostViewModel = viewModel(factory = postViewModelFactory) // Instancia de PostViewModel

                // --- Navegación Principal ---
                // Pasamos todos los ViewModels y el estado de la sesión a nuestro sistema de navegación.
                AppNavigation(
                    userViewModel = userViewModel,
                    productViewModel = productViewModel,
                    postViewModel = postViewModel, // Pasar el nuevo ViewModel
                    sharedPreferences = sharedPreferences
                )
            }
        }
    }
}
