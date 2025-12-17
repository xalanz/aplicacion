package com.example.pagina

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import com.example.pagina.data.local.User
import com.example.pagina.navegacion.AppRutas
import com.example.pagina.registro_inicio.InisioSeecion
import com.example.pagina.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class InisioSeecionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavController: NavController = mockk(relaxed = true)
    private val mockUserViewModel: UserViewModel = mockk(relaxed = true)
    private val onLoginSuccess: (Long) -> Unit = mockk(relaxed = true)

    @Test
    fun inicioSesion_successfulLogin() {
        val user = User(id = 1, name = "Alan", apellidos = "Turing", email = "alan.turing@gmail.com", password = "password123", direccion = "Bletchley Park")
        coEvery { mockUserViewModel.login("alan.turing@gmail.com", "password123") } returns user

        composeTestRule.setContent {
            InisioSeecion(navController = mockNavController, viewModel = mockUserViewModel, onLoginSuccess = onLoginSuccess)
        }

        composeTestRule.onNodeWithText("Email").performTextInput("alan.turing@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Iniciar Sesión").performClick()

        verify { onLoginSuccess(1L) }
    }

    @Test
    fun inicioSesion_invalidEmail_showsErrorMessage() {
        composeTestRule.setContent {
            InisioSeecion(navController = mockNavController, viewModel = mockUserViewModel, onLoginSuccess = onLoginSuccess)
        }

        composeTestRule.onNodeWithText("Email").performTextInput("invalid-email")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Iniciar Sesión").performClick()

        composeTestRule.onNodeWithText("El correo debe terminar con @gmail.com").assertIsDisplayed()
    }

    @Test
    fun inicioSesion_wrongCredentials_showsErrorMessage() {
        coEvery { mockUserViewModel.login(any(), any()) } returns null

        composeTestRule.setContent {
            InisioSeecion(navController = mockNavController, viewModel = mockUserViewModel, onLoginSuccess = onLoginSuccess)
        }

        composeTestRule.onNodeWithText("Email").performTextInput("alan.turing@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("wrongpassword")
        composeTestRule.onNodeWithText("Iniciar Sesión").performClick()

        composeTestRule.onNodeWithText("Usuario o contraseña incorrectos").assertIsDisplayed()
    }

    @Test
    fun inicioSesion_navigateToRegistro() {
        composeTestRule.setContent {
            InisioSeecion(navController = mockNavController, viewModel = mockUserViewModel, onLoginSuccess = onLoginSuccess)
        }

        composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate").performClick()

        verify { mockNavController.navigate(AppRutas.Registro.route) }
    }
}