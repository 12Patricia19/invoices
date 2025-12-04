package com.pucetec.products.models.entities

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    // createdAt -> created_at
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
}

/***
 *
 * products -> id, name, price, created_at, updated_at
 *
 * users -> id, name, email, ci, created_at, updated_at
 *
 * devices -> id, imei, serial_number, created_at, updated_at
 *
 *
 *
 * product: baseEntity -> name, price
 *
 * user: baseEntity -> name, email, ci
 *
 *
 */