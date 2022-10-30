package com.example.proiectdac

import java.time.Month
import java.util.*

data class CardInfo (
    var userId: Int = 0,
    var cardNumber: String = "",
    var cardHolder: String = "",
    var cardExpirationMonth: String = "",
    var cardExpirationYear: String = "",
    var CVV: String = ""
)