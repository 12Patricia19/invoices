package com.pucetec.products.services

import com.pucetec.products.mappers.InvoiceDetailMapper
import com.pucetec.products.models.entities.InvoiceDetail
import com.pucetec.products.models.requests.InvoiceDetailRequest
import com.pucetec.products.models.responses.InvoiceDetailResponse
import com.pucetec.products.repositories.InvoiceDetailRepository
import com.pucetec.products.repositories.InvoiceRepository
import com.pucetec.products.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class InvoiceDetailService(
    private val invoiceDetailRepository: InvoiceDetailRepository,
    private val invoiceDetailMapper: InvoiceDetailMapper,
    // repositorios ajenos
    private val productRepository: ProductRepository,
    private val invoiceRepository: InvoiceRepository
) {
    fun save(request: InvoiceDetailRequest): InvoiceDetailResponse {
        val productEntity = productRepository.findById(request.productId)
        val invoiceEntity = invoiceRepository.findById(request.invoiceId)

        val entity = InvoiceDetail(
            totalPrice = 0.0f,
            product = productEntity.get(),
            invoice = invoiceEntity.get()
        )

        val savedEntity = invoiceDetailRepository.save(entity)

        return invoiceDetailMapper.toResponse(savedEntity)
    }
}