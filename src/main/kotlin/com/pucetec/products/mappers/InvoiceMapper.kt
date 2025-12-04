package com.pucetec.products.mappers

import com.pucetec.products.models.entities.Invoice
import com.pucetec.products.models.requests.InvoiceRequest
import com.pucetec.products.models.responses.InvoiceDetailResponse
import com.pucetec.products.models.responses.InvoiceResponse
import com.pucetec.products.models.responses.ProductResponse
import org.springframework.stereotype.Component

@Component
class InvoiceMapper {
    fun toEntity(request: InvoiceRequest): Invoice {
        return Invoice(
            clientCi = request.clientCi,
            clientName = request.clientName,
            clientAddress = request.clientAddress,
            totalBeforeTaxes = request.totalBeforeTaxes,
            taxes = request.taxes,
            totalAfterTaxes = request.totalAfterTaxes,
        )
    }

    fun toResponse(invoice: Invoice): InvoiceResponse {
        return InvoiceResponse(
            clientCi = invoice.clientCi,
            clientName = invoice.clientName,
            clientAddress = invoice.clientAddress,
            totalBeforeTaxes = invoice.totalBeforeTaxes,
            taxes = invoice.taxes,
            totalAfterTaxes = invoice.totalAfterTaxes,
            invoiceDetails = invoice.invoiceDetails.map { invoiceDetail ->
                InvoiceDetailResponse(
                    id = invoiceDetail.id,
                    totalPrice = invoiceDetail.totalPrice,
                    product = ProductMapper().toResponse(invoiceDetail.product),
                    createdAt = invoiceDetail.createdAt,
                    updatedAt = invoiceDetail.updatedAt,
                )
            },
            id = invoice.id,
            createdAt = invoice.createdAt,
            updatedAt = invoice.updatedAt,
        )
    }
}

/**
 * invoice [
 *      invoice_details [
 *              products
 *              ]
 * ]
 */