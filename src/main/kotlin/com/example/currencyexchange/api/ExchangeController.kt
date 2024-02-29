package com.example.currencyexchange.api

import com.example.currencyexchange.domain.CurrencyExchangeRequest
import com.example.currencyexchange.domain.CurrencyExchangeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/currencyExchange")
class ExchangeController(
    private val currencyExchangeService: CurrencyExchangeService,
) {

    @PostMapping
    fun exchangeCurrency(@RequestBody request: CurrencyExchangeRequest): CurrencyExchangeResponseDto {
        val value = currencyExchangeService.calculateCurrencyExchange(request)

        return CurrencyExchangeResponseDto(
            currency = request.to_currency,
            value = value,
            date = request.date,
        )
    }
}

data class CurrencyExchangeResponseDto(
    val currency: String,
    val value: BigDecimal,
    val date: String,
)
