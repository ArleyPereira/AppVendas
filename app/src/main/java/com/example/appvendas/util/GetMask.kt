package com.example.appvendas.util

import java.text.*
import java.util.*

class GetMask {
    companion object {
        fun getValor(valor: Double): String {
            val nf: NumberFormat = DecimalFormat(
                "#,##0.00",
                DecimalFormatSymbols(Locale("pt", "BR"))
            )
            return nf.format(valor)
        }
    }
}