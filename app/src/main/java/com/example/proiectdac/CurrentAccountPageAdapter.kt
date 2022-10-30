package com.example.proiectdacmyvers

class CurrentAccountPageAdapter(private val account: CurrentAccount) {
    fun SetMoney(newValue: Int) {
        account.moneyAmount = newValue;

    }
}