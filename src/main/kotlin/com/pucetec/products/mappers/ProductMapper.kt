package com.pucetec.products.mappers

import com.pucetec.products.models.entities.Product
import com.pucetec.products.models.requests.ProductRequest
import com.pucetec.products.models.responses.ProductResponse
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toEntity(product: ProductRequest): Product {
        return Product(
            name = product.name,
            stock = product.stock,
            price = product.price,
        )
    }

    fun toResponse(product: Product): ProductResponse {
        return ProductResponse(
            name = product.name,
            stock = product.stock,
            price = product.price,
            id = product.id,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }
}


/**
 * controlador -> request
 * request -> entity
 * entity -> repository
 * entity -> response
 * response -> controlador
 */