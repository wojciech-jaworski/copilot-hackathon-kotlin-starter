package com.example.currencyexchange.domain

import java.time.LocalDate

interface CurrencyRepository {

    fun addCurrencyRate(currency: CurrencyRate)

    fun getCurrencyRateForDate(currency: String, date: LocalDate): CurrencyRate?

    fun getAllCurrencyRates(): List<CurrencyRate>

}