package com.pucetec.products.services

import com.pucetec.products.exceptions.ProductAlreadyExistsException
import com.pucetec.products.exceptions.ProductNotFoundException
import com.pucetec.products.exceptions.StockOutOfRangeException
import com.pucetec.products.mappers.ProductMapper
import com.pucetec.products.models.entities.Product
import com.pucetec.products.models.requests.ProductRequest
import com.pucetec.products.models.responses.ProductResponse
import com.pucetec.products.repositories.ProductRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

class ProductServiceTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var productMapper: ProductMapper
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productRepository = mock()
        productMapper = mock()
        productService = ProductService(productRepository, productMapper)
    }

    @Test
    fun `save debe guardar exitosamente un producto cuando el stock es menor a 10 y el nombre no existe`() {
        val request = ProductRequest(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 5
        )

        val entity = Product(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 5
        )

        val savedEntity = Product(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 5
        ).apply {
            id = 1L
        }

        val response = ProductResponse(
            id = 1L,
            name = "Producto de Prueba",
            price = 100.0,
            stock = 5,
            createdAt = savedEntity.createdAt,
            updatedAt = savedEntity.updatedAt
        )

        whenever(productRepository.findByName(request.name)).thenReturn(null)
        whenever(productMapper.toEntity(request)).thenReturn(entity)
        whenever(productRepository.save(entity)).thenReturn(savedEntity)
        whenever(productMapper.toResponse(savedEntity)).thenReturn(response)

        val result = productService.save(request)

        assertNotNull(result)
        assertEquals("Producto de Prueba", result.name)
        assertEquals(100.0, result.price)
        assertEquals(5, result.stock)
        verify(productRepository, times(1)).findByName(request.name)
        verify(productMapper, times(1)).toEntity(request)
        verify(productRepository, times(1)).save(entity)
        verify(productMapper, times(1)).toResponse(savedEntity)
    }

    @Test
    fun `save debe lanzar StockOutOfRangeException cuando el stock es 10`() {
        val request = ProductRequest(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 10
        )

        val exception = assertThrows<StockOutOfRangeException> {
            productService.save(request)
        }

        assertEquals("Stock out of range", exception.message)
        verifyNoInteractions(productRepository)
        verifyNoInteractions(productMapper)
    }

    @Test
    fun `save debe lanzar StockOutOfRangeException cuando el stock es mayor a 10`() {
        val request = ProductRequest(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 15
        )

        val exception = assertThrows<StockOutOfRangeException> {
            productService.save(request)
        }

        assertEquals("Stock out of range", exception.message)
        verifyNoInteractions(productRepository)
        verifyNoInteractions(productMapper)
    }

    @Test
    fun `save debe lanzar ProductAlreadyExistsException cuando el nombre del producto ya existe`() {
        val request = ProductRequest(
            name = "Producto Existente",
            price = 100.0,
            stock = 5
        )

        val existingProduct = Product(
            name = "Producto Existente",
            price = 100.0,
            stock = 5
        ).apply {
            id = 1L
        }

        whenever(productRepository.findByName(request.name)).thenReturn(existingProduct)

        val exception = assertThrows<ProductAlreadyExistsException> {
            productService.save(request)
        }

        assertEquals("Product already exists", exception.message)
        verify(productRepository, times(1)).findByName(request.name)
        verifyNoMoreInteractions(productRepository)
        verifyNoInteractions(productMapper)
    }

    @Test
    fun `save debe guardar exitosamente un producto con stock igual a 9`() {
        val request = ProductRequest(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 9
        )

        val entity = Product(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 9
        )

        val savedEntity = Product(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 9
        ).apply {
            id = 1L
        }

        val response = ProductResponse(
            id = 1L,
            name = "Producto de Prueba",
            price = 100.0,
            stock = 9,
            createdAt = savedEntity.createdAt,
            updatedAt = savedEntity.updatedAt
        )

        whenever(productRepository.findByName(request.name)).thenReturn(null)
        whenever(productMapper.toEntity(request)).thenReturn(entity)
        whenever(productRepository.save(entity)).thenReturn(savedEntity)
        whenever(productMapper.toResponse(savedEntity)).thenReturn(response)

        val result = productService.save(request)

        assertNotNull(result)
        assertEquals(9, result.stock)
        verify(productRepository, times(1)).save(entity)
    }

    @Test
    fun `findById debe retornar exitosamente un producto cuando existe`() {
        val productId = 1L
        val foundProduct = Product(
            name = "Producto de Prueba",
            price = 100.0,
            stock = 5
        ).apply {
            id = productId
        }

        val response = ProductResponse(
            id = productId,
            name = "Producto de Prueba",
            price = 100.0,
            stock = 5,
            createdAt = foundProduct.createdAt,
            updatedAt = foundProduct.updatedAt
        )

        whenever(productRepository.findById(productId)).thenReturn(Optional.of(foundProduct))
        whenever(productMapper.toResponse(foundProduct)).thenReturn(response)

        val result = productService.findById(productId)

        assertNotNull(result)
        assertEquals(productId, result.id)
        assertEquals("Producto de Prueba", result.name)
        assertEquals(100.0, result.price)
        assertEquals(5, result.stock)
        verify(productRepository, times(1)).findById(productId)
        verify(productMapper, times(1)).toResponse(foundProduct)
    }

    @Test
    fun `findById debe lanzar ProductNotFoundException cuando el producto no existe`() {
        val productId = 999L

        whenever(productRepository.findById(productId)).thenReturn(Optional.empty())

        val exception = assertThrows<ProductNotFoundException> {
            productService.findById(productId)
        }

        assertEquals("Product not found", exception.message)
        verify(productRepository, times(1)).findById(productId)
        verifyNoInteractions(productMapper)
    }

    @Test
    fun `save debe guardar exitosamente un producto con stock igual a 0`() {
        val request = ProductRequest(
            name = "Producto Sin Stock",
            price = 50.0,
            stock = 0
        )

        val entity = Product(
            name = "Producto Sin Stock",
            price = 50.0,
            stock = 0
        )

        val savedEntity = Product(
            name = "Producto Sin Stock",
            price = 50.0,
            stock = 0
        ).apply {
            id = 1L
        }

        val response = ProductResponse(
            id = 1L,
            name = "Producto Sin Stock",
            price = 50.0,
            stock = 0,
            createdAt = savedEntity.createdAt,
            updatedAt = savedEntity.updatedAt
        )

        whenever(productRepository.findByName(request.name)).thenReturn(null)
        whenever(productMapper.toEntity(request)).thenReturn(entity)
        whenever(productRepository.save(entity)).thenReturn(savedEntity)
        whenever(productMapper.toResponse(savedEntity)).thenReturn(response)

        val result = productService.save(request)

        assertNotNull(result)
        assertEquals(0, result.stock)
        verify(productRepository, times(1)).save(entity)
    }
}
