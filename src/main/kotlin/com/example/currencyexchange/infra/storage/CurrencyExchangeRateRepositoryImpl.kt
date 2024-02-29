package com.example.currencyexchange.infra.storage

import com.example.currencyexchange.domain.CurrencyExchangeRate
import com.example.currencyexchange.domain.CurrencyExchangeRateRepository
import org.springframework.stereotype.Repository

@Repository
class CurrencyExchangeRateRepositoryImpl : CurrencyExchangeRateRepository {

    var storage: MutableList<CurrencyExchangeRate> = ArrayList()

    override fun getAllCurrencyExchangeRates(): List<CurrencyExchangeRate> {
        return storage
    }

    override fun updateCurrencyExchangeRates(currencyExchangeRates: List<CurrencyExchangeRate>) {
        storage += currencyExchangeRates
    }
}
