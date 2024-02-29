package com.example.currencyexchange.api

import com.example.currencyexchange.domain.CurrencyExchangeRequest
import com.example.currencyexchange.domain.CurrencyExchangeResponse
import com.example.currencyexchange.domain.CurrencyFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CurrencyExchangeController(
    private val currencyFacade: CurrencyFacade
) {

    @PostMapping("/currencyExchange")
    fun exchangeCurrency(@RequestBody request: CurrencyExchangeRequest): ResponseEntity<CurrencyExchangeResponse> {
        val response = currencyFacade.exchangeCurrency(request)
        return ResponseEntity.ok(response)
    }
}