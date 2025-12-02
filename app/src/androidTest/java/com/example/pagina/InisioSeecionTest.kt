package com.example.pagina

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import com.example.pagina.data.local.User
import com.example.pagina.registro_inicio.InisioSeecion
import com.example.pagina.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InisioSeecionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockNavController: NavController
    private lateinit var mockUserViewModel: UserViewModel
    private lateinit var onLoginSuccess: (Long) -> Unit

    @Before
    fun setUp() {
        mockNavController = mockk(relaxed = true)
        mockUserViewModel = mockk(relaxed = true)
        onLoginSuccess = mockk(relaxed = true)

        composeTestRule.setContent {
            InisioSeecion(
                navController = mockNavController,
                viewModel = mockUserViewModel,
                onLoginSuccess = onLoginSuccess
            )
        }
    }

    @Test
    fun loginScreen_initialState_displaysAllElements() {
        composeTestRule.onNodeWithText("Inicio de Sesión").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
    }

    @Test
    fun loginButton_whenClickedWithValidInput_callsViewModelAndTriggersCallback() {
        // Arrange
        val fakeUser = User(
            id = 1,
            name = "Test User",
            apellidos = "Test Apellido",
            email = "test@gmail.com",
            password = "password123",
            direccion = "123 Fake Street",
            role = "USER"
        )
        coEvery { mockUserViewModel.login(any(), any()) } returns fakeUser

        // Act
        composeTestRule.onNodeWithText("Email").performTextInput("test@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password123")
        composeTestRule.onNodeWithText("Iniciar Sesión").performClick()

        // Espera a que la UI y las corrutinas terminen
        composeTestRule.waitForIdle()

        // Assert
        runBlocking {
            coVerify { mockUserViewModel.login("test@gmail.com", "password123") }
        }

        verify { onLoginSuccess(any()) }
    }
}