package com.example.currencyexchange.domain

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CurrencyExchangeService(
    private val currencyExchangeRateRepository: CurrencyExchangeRateRepository,
) {
    fun calculateCurrencyExchange(request: CurrencyExchangeRequest): BigDecimal {
        val currencyExchangeRate = currencyExchangeRateRepository.getAllCurrencyExchangeRates()
            .find {
                it.currency == request.from_currency
                    && it.date == request.date
            }
            ?: throw IllegalArgumentException("Currency not found")

        return currencyExchangeRate.pricePln * request.amount
    }
}

data class CurrencyExchangeRequest(
    val from_currency: String,
    val to_currency: String,
    val amount: BigDecimal,
    val date: String,
)
