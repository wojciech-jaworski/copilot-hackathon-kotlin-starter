package com.example.currencyexchange.domain

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CurrencyExchangeService(
    private val currencyExchangeRateRepository: CurrencyExchangeRateRepository,
) {
    fun calculateCurrencyExchange(request: CurrencyExchangeRequest): BigDecimal {
        val plnValue = calculatePlnValue(request)

        if (request.to_currency == "PLN") {
            return plnValue
        }

        return plnValue / getExchangeRate(request.to_currency, request.date).pricePln
    }

    private fun calculatePlnValue(request: CurrencyExchangeRequest): BigDecimal {
        val currencyExchangeRate = getExchangeRate(request.from_currency, request.date)
        return currencyExchangeRate.pricePln * request.amount
    }

    private fun getExchangeRate(
        currency: String,
        date: String,
    ): CurrencyExchangeRate {
        val currencyExchangeRate = currencyExchangeRateRepository.getAllCurrencyExchangeRates()
            .find {
                it.currency == currency
                    && it.date == date
            } ?: throw IllegalArgumentException("Currency not found")
        return currencyExchangeRate
    }
}

data class CurrencyExchangeRequest(
    val from_currency: String,
    val to_currency: String,
    val amount: BigDecimal,
    val date: String,
)
