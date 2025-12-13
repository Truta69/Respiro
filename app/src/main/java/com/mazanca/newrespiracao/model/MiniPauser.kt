package com.mazanca.newrespiracao.model;

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class MiniPauser(private val tempoPausaCustom: Long = 4) : Respirar, Parcelable {
    override fun getNome() = "Mini Pausa"
    override fun getTempoInspirar() = 2L
    override fun getTempoExpirar() = 4L
    override fun getNumeroDeCiclos() = 3L
    override fun getTempoPausa() = tempoPausaCustom
}



