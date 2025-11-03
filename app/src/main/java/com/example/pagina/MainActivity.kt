package com.example.pagina
// FORCE RE-COMPILE

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.pagina.data.local.AppDatabase
import com.example.pagina.data.repository.UserRepository
import com.example.pagina.navegacion.AppNavigation
import com.example.pagina.ui.theme.PaginaTheme
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

        val repo = UserRepository(db.userDao())
        val factory = UserViewModelFactory(repo)

        setContent {
            PaginaTheme {
                val viewModel: UserViewModel = viewModel(factory = factory)
                // NOW WE USE THE MAIN NAVIGATION
                AppNavigation(viewModel = viewModel)
            }        }
    }
}
