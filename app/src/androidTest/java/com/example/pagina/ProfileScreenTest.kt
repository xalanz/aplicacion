package com.example.pagina

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.pagina.data.local.User
import com.example.pagina.pantallas.ProfileScreen
import com.example.pagina.viewmodel.UserViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockUserViewModel: UserViewModel
    private lateinit var onLogout: () -> Unit
    private lateinit var onBack: () -> Unit

    private val fakeUser = User(
        id = 1,
        name = "Alan",
        apellidos = "Turing",
        email = "alan.turing@example.com",
        password = "password123",
        direccion = "123 Bletchley Park",
        role = "USER"
    )

    @Before
    fun setUp() {
        // 1. Inicializa los mocks para las dependencias
        mockUserViewModel = mockk(relaxed = true)
        onLogout = mockk(relaxed = true)
        onBack = mockk(relaxed = true)

        // 2. Simula el StateFlow del ViewModel para que emita el usuario falso
        val userFlow = MutableStateFlow<User?>(fakeUser)
        every { mockUserViewModel.latestUser } returns userFlow

        // 3. Establece el contenido de la prueba, pasando los mocks
        composeTestRule.setContent {
            ProfileScreen(
                viewModel = mockUserViewModel,
                onLogout = onLogout,
                onBack = onBack
            )
        }
    }

    @Test
    fun profileScreen_displaysUserDataCorrectly() {
        // Verifica que el título y los datos del usuario se muestren correctamente
        composeTestRule.onNodeWithText("Perfil de usuario").assertIsDisplayed()

        // Usamos onAllNodesWithText porque "Alan" aparece dos veces (cabecera y campo de texto)
        val alanNodes = composeTestRule.onAllNodesWithText("Alan")
        alanNodes.assertCountEquals(2) // Verifica que se encuentren dos nodos
        alanNodes[0].assertIsDisplayed()
        alanNodes[1].assertIsDisplayed()

        // Verifica el resto de los datos que son únicos
        composeTestRule.onNodeWithText("Turing").assertIsDisplayed()
        composeTestRule.onNodeWithText("alan.turing@example.com").assertIsDisplayed()

        composeTestRule.onNodeWithText("Cerrar Sesión").assertIsDisplayed()
    }

    @Test
    fun backButton_whenClicked_callsOnBackLambda() {
        // Act: Simula un clic en el icono de "Atrás" usando su descripción de contenido
        composeTestRule.onNodeWithContentDescription("Atrás").performClick()

        // Assert: Verifica que la función onBack fue llamada
        verify { onBack() }
    }

    @Test
    fun logoutButton_whenClicked_callsOnLogoutLambda() {
        // Act: Encuentra el botón "Cerrar Sesión" y haz clic en él
        composeTestRule.onNodeWithText("Cerrar Sesión").performClick()

        // Assert: Verifica que la función onLogout fue llamada
        verify { onLogout() }
    }
}