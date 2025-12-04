package com.pucetec.products.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "invoice_details")
data class InvoiceDetail (
    val totalPrice: Float,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    val invoice: Invoice,
): BaseEntity()