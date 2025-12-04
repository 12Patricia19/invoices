package com.pucetec.products.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column

class InvoiceRequest(
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
)

/**
 * {
 * "client_ci": "1738383883"
 * }
 */