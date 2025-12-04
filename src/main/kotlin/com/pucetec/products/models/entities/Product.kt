package com.pucetec.products.models.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "products")
data class Product (
    val name: String,
    val price: Double,
    val stock: Int,

    @OneToMany
    val invoiceDetails: List<InvoiceDetail> = emptyList(),
): BaseEntity()


/**
 * id, name, price, stock, created_at, updated_at
 */