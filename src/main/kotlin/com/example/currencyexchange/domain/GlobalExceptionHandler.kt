package com.example.currencyexchange.domain

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyRateAlreadyExistsException::class)
    fun handleCurrencyAlreadyExistsException(e: CurrencyRateAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.message)
    }

    @ExceptionHandler(CurrencyRateNotFoundException::class)
    fun handleCurrencyRateNotFoundException(e: CurrencyRateNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.message)
    }

}
