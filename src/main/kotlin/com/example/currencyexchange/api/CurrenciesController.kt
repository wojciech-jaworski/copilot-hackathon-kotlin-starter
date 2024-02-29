package com.example.currencyexchange.api

import com.example.currencyexchange.domain.CurrencyExchangeRate
import com.example.currencyexchange.domain.CurrencyExchangeRateRepository
import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/currency")
class CurrenciesController(
    private val currencyExchangeRateRepository: CurrencyExchangeRateRepository,
) {

    @GetMapping
    fun getCurrencies(): CurrencyExchangeRatesDto {
        return CurrencyExchangeRatesDto(
            currencyExchangeRateRepository.getAllCurrencyExchangeRates()
                .sortedBy { it.currency }
        )
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun updateCurrencies(@RequestBody currencyExchangeRates: CurrencyExchangeRatesDto) {
        currencyExchangeRateRepository.updateCurrencyExchangeRates(
            currencyExchangeRates.currencies
        )
    }
}

data class CurrencyExchangeRatesDto @JsonCreator constructor(
    val currencies: List<CurrencyExchangeRate>,
)
