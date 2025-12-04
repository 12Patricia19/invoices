package com.pucetec.products.controllers

import com.pucetec.products.models.requests.InvoiceRequest
import com.pucetec.products.models.responses.InvoiceResponse
import com.pucetec.products.services.InvoiceDetailService
import com.pucetec.products.services.InvoiceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/invoices"])
class InvoiceController(
    private val invoiceService: InvoiceService,
) {

    @PostMapping
    fun save(@RequestBody request: InvoiceRequest): InvoiceResponse {
        return invoiceService.save(request)
    }

    @GetMapping
    fun findAll(): List<InvoiceResponse> {
        return invoiceService.findAll()
    }

}