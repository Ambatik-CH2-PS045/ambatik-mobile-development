package com.example.ambatik.utlis

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Double): String {
    val localeID = Locale("id", "ID")
    val currencyFormat = NumberFormat.getCurrencyInstance(localeID)
    return currencyFormat.format(amount).toString()
}