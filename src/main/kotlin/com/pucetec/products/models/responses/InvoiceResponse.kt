package com.pucetec.products.models.responses

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class InvoiceResponse (
    var id: Long,
    @JsonProperty("client_ci")
    val clientCi: String,
    @JsonProperty("client_name")
    val clientName: String,
    @JsonProperty( "client_address")
    val clientAddress: String,
    @JsonProperty("total_before_taxes")
    val totalBeforeTaxes: Float? = null,
    val taxes: Float? = null,
    @JsonProperty("total_after_taxes")
    val totalAfterTaxes: Float? = null,

    @JsonProperty("invoice_details")
    val invoiceDetails: List<InvoiceDetailResponse> = emptyList(),

    @JsonProperty( "created_at")
    val createdAt: LocalDateTime,

    @JsonProperty("updated_at")
    val updatedAt: LocalDateTime
    )