package com.example.currencyexchange.infra.storage

import com.example.currencyexchange.domain.CurrencyExchangeRate
import com.example.currencyexchange.domain.CurrencyExchangeRateRepository
import org.springframework.stereotype.Repository

@Repository
class CurrencyExchangeRateRepositoryImpl : CurrencyExchangeRateRepository {

    var storage: MutableSet<CurrencyExchangeRate> = LinkedHashSet()

    override fun getAllCurrencyExchangeRates(): List<CurrencyExchangeRate> {
        return storage.toList()
    }

    override fun updateCurrencyExchangeRates(currencyExchangeRates: List<CurrencyExchangeRate>) {
        storage += currencyExchangeRates
    }
}
