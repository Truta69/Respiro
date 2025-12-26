package com.mazanca.newrespiracao.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RespiracaoProfunda(
    private val tempoPausa: Long = 7
) : Respirar, Parcelable {
    override fun getNome() = "Relaxamento Profundo"
    override fun getTempoInspirar() = 4L
    override fun getTempoExpirar() = 8L
    override fun getNumeroDeCiclos() = 4L
    override fun getTempoPausa() = tempoPausa
}