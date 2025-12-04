package com.pucetec.products.services

import com.pucetec.products.exceptions.ProductAlreadyExistsException
import com.pucetec.products.exceptions.ProductNotFoundException
import com.pucetec.products.exceptions.StockOutOfRangeException
import com.pucetec.products.mappers.ProductMapper
import com.pucetec.products.models.entities.Product
import com.pucetec.products.models.requests.ProductRequest
import com.pucetec.products.models.responses.ProductResponse
import com.pucetec.products.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) {

    /**
     * Tengo un almacén en el que solo puedo guardar
     * 10 existencias de cada producto. Si intento guardar mas
     * debo dar un error
     */

    fun save(request: ProductRequest): ProductResponse {
        if (request.stock >= 10) {
            throw StockOutOfRangeException("Stock out of range")
        }

        if (productRepository.findByName(request.name) != null) {
            throw ProductAlreadyExistsException("Product already exists")
        }

        val entity = productMapper.toEntity(request)

        val savedProduct = productRepository.save(entity)

        return productMapper.toResponse(savedProduct)

    }

    // 005_exception_management_apellido_nombre
    // si el producto es buscado con un id que no existe, retornar http 404
    fun findById(id: Long): ProductResponse {
        val foundProduct = productRepository.findById(id).orElse(null)

        if (foundProduct == null) {
            throw ProductNotFoundException("Product not found")
        }

        return productMapper.toResponse(foundProduct)
    }
}


/***
 * controllers -> manejo de peticiones http
 *
 * services -> logica del negocio
 *
 * repositories -> dedican al acceso de data
 */


