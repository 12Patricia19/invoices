package com.pucetec.products.mappers

import com.pucetec.products.models.entities.InvoiceDetail
import com.pucetec.products.models.requests.InvoiceDetailRequest
import com.pucetec.products.models.responses.InvoiceDetailResponse
import org.springframework.stereotype.Component

@Component
class InvoiceDetailMapper {
    fun toResponse(invoiceDetail: InvoiceDetail): InvoiceDetailResponse {
        return InvoiceDetailResponse(
            id = invoiceDetail.id,
            createdAt = invoiceDetail.createdAt,
            updatedAt = invoiceDetail.updatedAt,
            product = ProductMapper().toResponse(invoiceDetail.product),
            totalPrice = invoiceDetail.totalPrice,
        )
    }
}