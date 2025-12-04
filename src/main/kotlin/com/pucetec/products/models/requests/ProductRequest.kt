package com.pucetec.products.models.requests

data class ProductRequest(
    val name: String,
    val price: Double,
    val stock: Int,
)