package com.example.currencyexchange.domain

data class CurrencyExchangeRate(
    val currency: String,
    val price_pln: String,
    val date: String,
)


interface CurrencyExchangeRateRepository {
    fun getAllCurrencyExchangeRates(): List<CurrencyExchangeRate>
    fun updateCurrencyExchangeRates(currencyExchangeRates: List<CurrencyExchangeRate>)
}
