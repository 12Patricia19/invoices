package com.pucetec.products.exceptions.handlers

import com.pucetec.products.exceptions.ProductAlreadyExistsException
import com.pucetec.products.exceptions.ProductNotFoundException
import com.pucetec.products.exceptions.StockOutOfRangeException
import com.pucetec.products.models.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Manejador de excepciones
 * Traducir excepciones hacia codigos http
 */

@RestControllerAdvice
class GlobalExceptionHandler {

    // convertir un error de tipo StockOutOfRangeException -> Http BAD REQUEST -> 400
    @ExceptionHandler(StockOutOfRangeException::class)
    fun handleStockOutOfRangeException(
        ex: StockOutOfRangeException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(ProductAlreadyExistsException::class)
    fun handleProductAlreadyExistsException(
        ex: ProductAlreadyExistsException
    ): ResponseEntity<ErrorResponse>{
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFoundException(
        ex: ProductNotFoundException
    ): ResponseEntity<ErrorResponse>{
        return ResponseEntity(
            ErrorResponse(ex.message),
            HttpStatus.NOT_FOUND
        )
    }
}



/**
 * 200 -> OK
 *
 * 400 -> Bad Request
 *
 * 404 -> Not Found
 *
 * 403 -> Forbidden
 *
 * 401 -> Unauthorized
 *
 * 5xx -> Error del sistema SIN CAPTURAR (EVITAR A TODA COSTA)
 * SI VEO ESTO EN EL EXAMEN SON PUNTOS MENOS
 */