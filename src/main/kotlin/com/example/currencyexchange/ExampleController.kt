package com.example.currencyexchange

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {
    @GetMapping("/hello")
    fun greeting(): String {
        return "Hello there!"
    }
}
