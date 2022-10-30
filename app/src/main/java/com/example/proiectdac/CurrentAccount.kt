package com.example.proiectdac

data class CurrentAccount (
    var id: Int = 0,
    var moneyAmount: Int,
    val currency: String = "RON"
)
