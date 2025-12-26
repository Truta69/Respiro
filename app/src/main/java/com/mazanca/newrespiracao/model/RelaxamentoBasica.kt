package com.mazanca.newrespiracao.model;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize;

@Parcelize
data class RelaxamentoBasica(
    private val tempoPausaCustom: Long = 6) : Respirar, Parcelable {
    override fun getNome() = "Relaxamento basica"
    override fun getTempoInspirar() = 6L
    override fun getTempoExpirar() = 6L
    override fun getNumeroDeCiclos() = 4L
    override fun getTempoPausa() = tempoPausaCustom
}


