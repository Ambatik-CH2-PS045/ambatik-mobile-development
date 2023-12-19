package com.example.ambatik.utlis

import java.text.SimpleDateFormat
import java.util.Locale

fun convertDateFormat(isoDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    try {
        val date = inputFormat.parse(isoDate)
        return outputFormat.format(date!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}