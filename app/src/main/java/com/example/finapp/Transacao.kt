package com.example.finapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Transacao(
    val valor: Double,
    val Tipo: String,
    val Descricao: String,
    val Data: Date
): Parcelable
