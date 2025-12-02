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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegistroTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockNavController: NavController
    private lateinit var mockUserViewModel: UserViewModel

    @Before
    fun setUp() {
        // 1. Inicializa los mocks para las dependencias
        mockNavController = mockk(relaxed = true)
        mockUserViewModel = mockk(relaxed = true)

        // 2. Establece el contenido de la prueba, pasando los mocks al Composable
        composeTestRule.setContent {
            Registro(
                navController = mockNavController,
                viewModel = mockUserViewModel
            )
        }
    }

    @Test
    fun registroScreen_initialState_displaysAllFormFields() {
        // Verifica que todos los campos de texto y el botón estén visibles
        composeTestRule.onNodeWithText("Nombre").assertIsDisplayed()
        composeTestRule.onNodeWithText("Apellido").assertIsDisplayed()
        composeTestRule.onNodeWithText("Correo electrónico").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
        composeTestRule.onNodeWithText("Verificar contraseña").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dirección").assertIsDisplayed()
        composeTestRule.onNodeWithText("Registrarse").assertIsDisplayed()
    }

    @Test
    fun registerButton_whenClickedWithValidData_callsViewModelAndNavigatesBack() {
        // Arrange: Define datos de entrada válidos que cumplan las condiciones de tu pantalla
        val nombre = "Test"
        val apellidos = "User"
        val correo = "test@gmail.com" // Debe terminar en @gmail.com
        val password = "password123"    // Debe tener al menos 6 caracteres
        val direccion = "123 Fake Street"

        // Act: Encuentra los campos, escribe los datos y haz clic en el botón
        composeTestRule.onNodeWithText("Nombre").performTextInput(nombre)
        composeTestRule.onNodeWithText("Apellido").performTextInput(apellidos)
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput(correo)
        composeTestRule.onNodeWithText("Contraseña").performTextInput(password)
        composeTestRule.onNodeWithText("Verificar contraseña").performTextInput(password) // Usa la misma contraseña
        composeTestRule.onNodeWithText("Dirección").performTextInput(direccion)

        composeTestRule.onNodeWithText("Registrarse").performClick()

        // Assert: Verifica que las acciones correctas ocurrieron
        // 1. Verifica que la función `addUser` en el ViewModel fue llamada con los datos correctos
        verify {
            mockUserViewModel.addUser(nombre, apellidos, correo, password, direccion)
        }

        // 2. Verifica que se llamó a `popBackStack` para volver a la pantalla anterior
        verify { mockNavController.popBackStack() }
    }
}