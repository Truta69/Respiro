package com.mazanca.newrespiracao.model;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize;

@Parcelize
data class RelaxamentoProfundo(
    private val tempoPausaCustom: Long = 6) : Respirar, Parcelable {
    override fun getNome() = "Relaxamento profundo"
    override fun getTempoInspirar() = 6L
    override fun getTempoExpirar() = 6L
    override fun getNumeroDeCiclos() = 4L
    override fun getTempoPausa() = tempoPausaCustom
}


