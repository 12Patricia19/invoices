package com.pucetec.products.controllers

import com.pucetec.products.models.entities.Product
import com.pucetec.products.models.requests.ProductRequest
import com.pucetec.products.models.responses.ProductResponse
import com.pucetec.products.services.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/products"])
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping
    fun save(@RequestBody product: ProductRequest): ProductResponse {
        return productService.save(product)
    }

    @GetMapping
    fun findById(@RequestParam id: Long): ProductResponse? {
        return productService.findById(id)
    }
}