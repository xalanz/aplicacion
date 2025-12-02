package com.example.pagina.api.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.pagina.api.model.Product
import com.example.pagina.api.viewmodel.PostViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockViewModel: PostViewModel
    private lateinit var fakeFlow: MutableStateFlow<List<Product>>

    // Crea una lista falsa de productos para usarla en las pruebas
    private val fakeProductList = listOf(
        Product(id = 1, name = "Laptop Gamer", categories = "Electrónica", price = 1200, image = "", stock = true, discount = false, stars = 5),
        Product(id = 2, name = "Teclado Mecánico", categories = "Accesorios", price = 150, image = "", stock = true, discount = true, stars = 4)
    )

    @Before
    fun setUp() {
        // 1. Inicializa el ViewModel y el Flow mockeado
        mockViewModel = mockk(relaxed = true)
        fakeFlow = MutableStateFlow(fakeProductList) // El estado inicial tiene productos

        // 2. Define el comportamiento del mock. Cuando se acceda a `postList`,
        // devolverá nuestro Flow, al que mantenemos una referencia.
        every { mockViewModel.postList } returns fakeFlow

        // 3. Establece el contenido para todas las pruebas.
        composeTestRule.setContent {
            PostScreen(viewModel = mockViewModel)
        }
    }

    @Test
    fun postScreen_displaysTitleAndProducts() {
        // Esta prueba verifica el estado inicial establecido en setUp()
        // 1. Verifica que el título en la TopAppBar se muestre
        composeTestRule.onNodeWithText("Listado de posts").assertIsDisplayed()

        // 2. Verifica que el primer producto de la lista falsa se muestre correctamente
        composeTestRule.onNodeWithText("Titulo: Laptop Gamer").assertIsDisplayed()
        composeTestRule.onNodeWithText("Post: Electrónica").assertIsDisplayed()

        // 3. Verifica que el segundo producto también se muestre
        composeTestRule.onNodeWithText("Titulo: Teclado Mecánico").assertIsDisplayed()
        composeTestRule.onNodeWithText("Post: Accesorios").assertIsDisplayed()
    }

    @Test
    fun postScreen_whenProductListIsEmpty_showsNoProducts() {
        // Arrange: Actualiza el valor del Flow a una lista vacía para simular un cambio de estado
        fakeFlow.value = emptyList()

        // La UI se recompone automáticamente. No se necesita setContent de nuevo.

        // Assert: Verifica que el título se muestre, pero el texto de los productos no exista
        composeTestRule.onNodeWithText("Listado de posts").assertIsDisplayed()
        composeTestRule.onNodeWithText("Titulo: Laptop Gamer").assertDoesNotExist()
        composeTestRule.onNodeWithText("Titulo: Teclado Mecánico").assertDoesNotExist()
    }
}
