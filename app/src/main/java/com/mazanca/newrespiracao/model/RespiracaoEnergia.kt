package com.mazanca.newrespiracao.model;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RespiracaoEnergia( private val tempoPausaCustom: Long = 0): Respirar,Parcelable {
    override fun getNome()="Respiracao Energizante"
    override fun getTempoInspirar()=2L
    override fun getTempoExpirar()=2L
    override fun getNumeroDeCiclos()=8L
}


