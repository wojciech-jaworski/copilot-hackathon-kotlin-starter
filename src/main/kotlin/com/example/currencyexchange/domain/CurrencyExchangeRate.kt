package com.example.currencyexchange.domain

import java.math.BigDecimal

data class CurrencyExchangeRate(
    val currency: String,
    val pricePln: BigDecimal,
    val date: String,
)


interface CurrencyExchangeRateRepository {
    fun getAllCurrencyExchangeRates(): List<CurrencyExchangeRate>
    fun updateCurrencyExchangeRates(currencyExchangeRates: List<CurrencyExchangeRate>)
}
