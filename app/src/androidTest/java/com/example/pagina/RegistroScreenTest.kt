package com.example.pagina

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import com.example.pagina.registro_inicio.Registro
import com.example.pagina.viewmodel.UserViewModel
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class RegistroScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavController: NavController = mockk(relaxed = true)
    private val mockUserViewModel: UserViewModel = mockk(relaxed = true)

    @Test
    fun registroScreen_successfulRegistration() {
        composeTestRule.setContent {
            Registro(navController = mockNavController, viewModel = mockUserViewModel)
        }

        // Rellenar los campos del formulario
        composeTestRule.onNodeWithText("Nombre").performTextInput("Alan")
        composeTestRule.onNodeWithText("Apellido").performTextInput("Turing")
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("alan.turing@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Verificar contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Dirección").performTextInput("Bletchley Park")

        // Hacer clic en el botón de registro
        composeTestRule.onNodeWithText("Registrarse").performClick()

        // Verificar que se llama a addUser y popBackStack
        verify { mockUserViewModel.addUser("Alan", "Turing", "alan.turing@gmail.com", "password123", "Bletchley Park") }
        verify { mockNavController.popBackStack() }
    }

    @Test
    fun registroScreen_emptyFields_showsErrorMessage() {
        composeTestRule.setContent {
            Registro(navController = mockNavController, viewModel = mockUserViewModel)
        }

        // Hacer clic en el botón de registro con los campos vacíos
        composeTestRule.onNodeWithText("Registrarse").performClick()

        // Verificar que se muestra el mensaje de error
        composeTestRule.onNodeWithText("Todos los campos son obligatorios").assertIsDisplayed()
    }

    @Test
    fun registroScreen_invalidEmail_showsErrorMessage() {
        composeTestRule.setContent {
            Registro(navController = mockNavController, viewModel = mockUserViewModel)
        }

        // Rellenar los campos del formulario con un email inválido
        composeTestRule.onNodeWithText("Nombre").performTextInput("Alan")
        composeTestRule.onNodeWithText("Apellido").performTextInput("Turing")
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("alan.turing@invalid.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Verificar contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Dirección").performTextInput("Bletchley Park")

        // Hacer clic en el botón de registro
        composeTestRule.onNodeWithText("Registrarse").performClick()

        // Verificar que se muestra el mensaje de error
        composeTestRule.onNodeWithText("El correo debe ser @gmail.com").assertIsDisplayed()
    }

    @Test
    fun registroScreen_passwordTooShort_showsErrorMessage() {
        composeTestRule.setContent {
            Registro(navController = mockNavController, viewModel = mockUserViewModel)
        }

        // Rellenar los campos del formulario con una contraseña demasiado corta
        composeTestRule.onNodeWithText("Nombre").performTextInput("Alan")
        composeTestRule.onNodeWithText("Apellido").performTextInput("Turing")
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("alan.turing@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("12345")
        composeTestRule.onNodeWithText("Verificar contraseña").performTextInput("12345")
        composeTestRule.onNodeWithText("Dirección").performTextInput("Bletchley Park")

        // Hacer clic en el botón de registro
        composeTestRule.onNodeWithText("Registrarse").performClick()

        // Verificar que se muestra el mensaje de error
        composeTestRule.onNodeWithText("La contraseña debe tener al menos 6 caracteres").assertIsDisplayed()
    }

    @Test
    fun registroScreen_passwordsDoNotMatch_showsErrorMessage() {
        composeTestRule.setContent {
            Registro(navController = mockNavController, viewModel = mockUserViewModel)
        }

        // Rellenar los campos del formulario con contraseñas que no coinciden
        composeTestRule.onNodeWithText("Nombre").performTextInput("Alan")
        composeTestRule.onNodeWithText("Apellido").performTextInput("Turing")
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("alan.turing@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Verificar contraseña").performTextInput("password456")
        composeTestRule.onNodeWithText("Dirección").performTextInput("Bletchley Park")

        // Hacer clic en el botón de registro
        composeTestRule.onNodeWithText("Registrarse").performClick()

        // Verificar que se muestra el mensaje de error
        composeTestRule.onNodeWithText("Las contraseñas no coinciden").assertIsDisplayed()
    }
}