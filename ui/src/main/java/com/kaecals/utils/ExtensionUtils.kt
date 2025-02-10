package com.kaecals.utils

fun maskJackpotWinnerName(name: String): String {
    val firstPart = name.substring(0, 2)
    val lastPart = name.takeLast(3)
    val maskedPart = "*".repeat(name.length - 5)
    return "$firstPart$maskedPart$lastPart"
}