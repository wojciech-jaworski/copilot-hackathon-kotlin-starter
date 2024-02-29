package com.example.currencyexchange.api

import com.example.currencyexchange.domain.CurrencyFacade
import com.example.currencyexchange.domain.CurrencyRate
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class CurrencyController(
    private val currencyFacade: CurrencyFacade
) {

    @PostMapping(
        path = ["/currency"],
        consumes = ["application/json"]
    )
    fun addCurrencyRates(
        @RequestBody @NotNull currencies: CurrencyRatesDto
    ): ResponseEntity<Unit> {
        currencyFacade.addCurrencyRates(currencies.currencies.map { it.toDomain() })
        return ResponseEntity.status(CREATED).build()
    }

    @GetMapping("/currency")
    fun getAllCurrencyRates(): ResponseEntity<CurrencyRatesDto> {
        val currencyRates = currencyFacade.getAllCurrencyRates()
        return ResponseEntity.ok(CurrencyRatesDto(currencyRates.map { it.toDto() }))
    }

}

data class CurrencyRatesDto(
    val currencies: List<CurrencyRateDto>
)

data class CurrencyRateDto(
    val currency: String,
    val price_pln: String,
    val date: String
) {
    fun toDomain(): CurrencyRate {
        return CurrencyRate(currency, price_pln.toBigDecimal(), LocalDate.parse(date))
    }
}

fun CurrencyRate.toDto(): CurrencyRateDto {
    return CurrencyRateDto(currency, pricePln.toString(), date.toString())
}
