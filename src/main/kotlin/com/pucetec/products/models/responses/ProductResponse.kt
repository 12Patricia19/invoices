package com.pucetec.products.models.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Double,
    val stock: Int,
    @JsonProperty( "created_at")
    val createdAt: LocalDateTime,

    @JsonProperty("updated_at")
    val updatedAt: LocalDateTime
)