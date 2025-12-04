package com.pucetec.products.models.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class InvoiceDetailResponse(
    val id: Long,
    @JsonProperty("total_price")
    val totalPrice: Float,
    val product: ProductResponse,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,

    @JsonProperty("updated_at")
    val updatedAt: LocalDateTime
)