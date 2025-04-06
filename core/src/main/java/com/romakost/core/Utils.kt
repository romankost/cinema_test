package com.romakost.core

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Double.toOneSymbolSting(): String {
    return String.format("%.1f", this)
}
