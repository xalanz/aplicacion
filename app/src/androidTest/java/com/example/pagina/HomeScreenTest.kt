package com.example.pagina

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import com.example.pagina.navegacion.AppRutas
import com.example.pagina.pantallas.HomeScreen
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        // 1. Inicializa el mock del NavController
        mockNavController = mockk(relaxed = true)

        // 2. Establece el contenido de la prueba, pasando el mock
        composeTestRule.setContent {
            HomeScreen(navController = mockNavController)
        }
    }

    @Test
    fun homeScreen_initialState_displaysAllElements() {
        // Verifica que el título y los elementos principales estén visibles
        composeTestRule.onNodeWithText("Level Up Gaming").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hola, ¡qué bueno verte!").assertIsDisplayed()

        // Verifica que las tarjetas de navegación principales se muestren usando sus subtítulos únicos
        composeTestRule.onNodeWithText("Explora nuestros productos").assertIsDisplayed()
        composeTestRule.onNodeWithText("Descubre eventos próximos").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tu información").assertIsDisplayed()
    }

    @Test
    fun navigationCard_whenTiendaClicked_navigatesToTienda() {
        // Act: Simula un clic en la tarjeta de la Tienda usando su subtítulo único
        composeTestRule.onNodeWithText("Explora nuestros productos").performClick()

        // Assert: Verifica que se navega a la ruta correcta
        verify { mockNavController.navigate(AppRutas.Tienda.route) }
    }

    @Test
    fun navigationCard_whenEventosClicked_navigatesToEventos() {
        // Act: Simula un clic en la tarjeta de Eventos usando su subtítulo único
        composeTestRule.onNodeWithText("Descubre eventos próximos").performClick()

        // Assert: Verifica que se navega a la ruta correcta
        verify { mockNavController.navigate(AppRutas.Eventos.route) }
    }

    @Test
    fun navigationCard_whenPerfilClicked_navigatesToProfile() {
        // Act: Simula un clic en la tarjeta de Perfil usando su subtítulo único
        composeTestRule.onNodeWithText("Tu información").performClick()

        // Assert: Verifica que se navega a la ruta correcta
        verify { mockNavController.navigate(AppRutas.Profile.route) }
    }
}