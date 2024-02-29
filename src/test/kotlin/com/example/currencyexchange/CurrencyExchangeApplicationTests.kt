package com.example.currencyexchange

import com.example.currencyexchange.api.CurrencyExchangeRateDto
import com.example.currencyexchange.api.CurrencyExchangeRatesDto
import com.example.currencyexchange.domain.CurrencyExchangeRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyExchangeApplicationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @BeforeEach
    fun setUp() {
        val request = CurrencyExchangeRatesDto(
            listOf(
                CurrencyExchangeRateDto(
                    currency = "EUR",
                    price_pln = "4.31",
                    date = "2023-01-01"
                ),
                CurrencyExchangeRateDto(
                    currency = "USD",
                    price_pln = "3.98",
                    date = "2023-01-01"
                )
            )
        )

        restTemplate.postForEntity("/currency", request, String::class.java)
    }

    @Test
    fun `test currency endpoint`() {
        // when
        val responseEntity = restTemplate.getForEntity("/currency", String::class.java)

        // then
        assert(responseEntity.statusCode.is2xxSuccessful)
        assert(responseEntity.body!! == "{\"currencies\":[{\"currency\":\"EUR\",\"price_pln\":\"4.31\",\"date\":\"2023-01-01\"},{\"currency\":\"USD\",\"price_pln\":\"3.98\",\"date\":\"2023-01-01\"}]}")
    }

    @Test
    fun `test currencyExchange endpoint`() {
        // given
        val request = CurrencyExchangeRequest(
            from_currency = "EUR",
            to_currency = "USD",
            amount = 100.toBigDecimal(),
            date = "2023-01-01"
        )

        // when
        val responseEntity = restTemplate.postForEntity("/currencyExchange", request, String::class.java)

        // then

        println(responseEntity.body!!)

        assert(responseEntity.statusCode.is2xxSuccessful)
        assert(responseEntity.body!! == "{\"currency\":\"USD\",\"value\":108.29,\"date\":\"2023-01-01\"}")
    }
}
