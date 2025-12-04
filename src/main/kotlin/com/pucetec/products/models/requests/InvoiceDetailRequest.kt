package com.pucetec.products.models.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class InvoiceDetailRequest(
    @JsonProperty("product_id")
    val productId: Long,
    @JsonProperty("invoice_id")
    val invoiceId: Long,
)