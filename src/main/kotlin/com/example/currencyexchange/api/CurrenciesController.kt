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
import java.math.BigDecimal

@RestController
@RequestMapping("/currency")
class CurrenciesController(
    private val currencyExchangeRateRepository: CurrencyExchangeRateRepository,
) {

    @GetMapping
    fun getCurrencies(): CurrencyExchangeRatesDto {
        return CurrencyExchangeRatesDto(
            currencyExchangeRateRepository.getAllCurrencyExchangeRates()
                .map { it.toDto() }
                .sortedBy { it.currency }
        )
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun updateCurrencies(@RequestBody currencyExchangeRates: CurrencyExchangeRatesDto) {
        currencyExchangeRateRepository.updateCurrencyExchangeRates(
            currencyExchangeRates.currencies
                .map { it.toDomain() }
        )
    }
}

data class CurrencyExchangeRatesDto @JsonCreator constructor(
    val currencies: List<CurrencyExchangeRateDto>,
)

data class CurrencyExchangeRateDto(
    val currency: String,
    val price_pln: String,
    val date: String,
)

fun CurrencyExchangeRate.toDto() = CurrencyExchangeRateDto(
    currency = currency,
    price_pln = price_pln.stripTrailingZeros().toPlainString(),
    date = date,
)

fun CurrencyExchangeRateDto.toDomain() = CurrencyExchangeRate(
    currency = currency,
    price_pln = BigDecimal(price_pln),
    date = date,
)
