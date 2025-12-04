package com.pucetec.products.repositories

import com.pucetec.products.models.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    fun findByName(name: String): Product?
}