package com.example.currencyexchange.infrastructure

import com.example.currencyexchange.domain.CurrencyRate
import com.example.currencyexchange.domain.CurrencyRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class InMemoryCurrencyRepository : CurrencyRepository {

    private val currencyRates = mutableListOf<CurrencyRate>()

    override fun addCurrencyRate(currency: CurrencyRate) {
        currencyRates.add(currency)
    }

    override fun getCurrencyRateForDate(currency: String, date: LocalDate): CurrencyRate? {
        return currencyRates.find { it.currency == currency && it.date == date }
    }

    override fun getAllCurrencyRates(): List<CurrencyRate> {
        return currencyRates.toList().sortedWith(compareBy({ it.currency }, { it.date }))
    }
}
