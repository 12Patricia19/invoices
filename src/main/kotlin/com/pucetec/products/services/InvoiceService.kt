package com.pucetec.products.services

import com.pucetec.products.mappers.InvoiceMapper
import com.pucetec.products.models.entities.InvoiceDetail
import com.pucetec.products.models.requests.InvoiceRequest
import com.pucetec.products.models.responses.InvoiceResponse
import com.pucetec.products.repositories.InvoiceDetailRepository
import com.pucetec.products.repositories.InvoiceRepository
import org.springframework.stereotype.Service

@Service
class InvoiceService(
    private val invoiceRepository: InvoiceRepository,
    private val invoiceMapper: InvoiceMapper,
    private val invoiceDetailsRepository: InvoiceDetailRepository
){
    fun save(request: InvoiceRequest): InvoiceResponse {
        val entity = invoiceMapper.toEntity(request)
        val savedInvoice = invoiceRepository.save(entity)
        return invoiceMapper.toResponse(savedInvoice)
    }

    fun findAll(): List<InvoiceResponse> {
        val invoices = invoiceRepository.findAll()
        invoices.map{ invoiceFromDatabase ->
            invoiceFromDatabase.invoiceDetails = invoiceDetailsRepository.findAllByInvoiceId(invoiceFromDatabase.id).toMutableList()
        }
        return invoices.map { invoice ->
            invoiceMapper.toResponse(invoice)
        }
    }
}