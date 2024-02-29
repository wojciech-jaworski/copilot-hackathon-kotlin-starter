package com.example.currencyexchange.domain

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class CurrencyFacade(
    private val currencyRepository: CurrencyRepository
) {

    fun addCurrencyRates(currencyRates: List<CurrencyRate>) {
        currencyRates.mapNotNull { currencyRepository.getCurrencyRateForDate(it.currency, it.date) }
            .forEach { throw CurrencyRateAlreadyExistsException(it.currency, it.date) }
        currencyRates.forEach(currencyRepository::addCurrencyRate)
    }

    fun getAllCurrencyRates(): List<CurrencyRate> {
        return currencyRepository.getAllCurrencyRates()
    }

    fun exchangeCurrency(request: CurrencyExchangeRequest): CurrencyExchangeResponse {
        val fromCurrencyRate = getCurrencyRate(request.from_currency, request.date)
        val toCurrencyRate = getCurrencyRate(request.to_currency, request.date)
        val value = fromCurrencyRate * request.amount / toCurrencyRate
        return CurrencyExchangeResponse(request.to_currency, value, request.date)
    }

    private fun getCurrencyRate(currencyName: String, date: LocalDate): BigDecimal {
        return if (currencyName == "PLN") BigDecimal("1.00")
        else currencyRepository.getCurrencyRateForDate(currencyName, date)?.pricePln
            ?: throw CurrencyRateNotFoundException(currencyName, date)
    }

}

data class CurrencyRate(
    val currency: String,
    val pricePln: BigDecimal,
    val date: LocalDate
)


data class CurrencyExchangeRequest(
    val from_currency: String,
    val to_currency: String,
    val amount: BigDecimal,
    val date: LocalDate
)

data class CurrencyExchangeResponse(
    val currency: String,
    val value: BigDecimal,
    val date: LocalDate
)

class CurrencyRateAlreadyExistsException(currency: String, date: LocalDate) :
    IllegalArgumentException("Currency rate for $currency and date $date already exists")

class CurrencyRateNotFoundException(currency: String, date: LocalDate) :
    IllegalArgumentException("Currency rate for $currency and date $date not found")
