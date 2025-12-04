package com.pucetec.products.repositories

import com.pucetec.products.models.entities.Invoice
import com.pucetec.products.models.entities.InvoiceDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface InvoiceDetailRepository: JpaRepository<InvoiceDetail, Long>{
    @Query("SELECT d FROM InvoiceDetail d WHERE d.invoice.id = :invoiceId")
    fun findAllByInvoiceId(@Param("invoiceId") invoiceId: Long): List<InvoiceDetail>
}