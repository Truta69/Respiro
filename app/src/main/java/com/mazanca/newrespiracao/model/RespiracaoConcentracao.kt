package com.mazanca.newrespiracao.model;

/**
 Trnasforma os objetos em parceleble e ai pode enviar pelo intent
 a interface que o android usa para enviar
 */

import kotlinx.parcelize.Parcelize;
import android.os.Parcelable;

/**
 * gera a implemtacao do parceleble
 * data class gera a implentacao dos atributos da classe
 */
@Parcelize
data class RespiracaoConcentracao(
        private val tempoPausaCustom:Long=4
):Respirar,Parcelable {
    override fun getNome() = "Relaxamento Concentração"
    override fun getTempoInspirar() = 4L
    override fun getTempoExpirar() = 4L
    override fun getNumeroDeCiclos() = 4L
    override fun getTempoPausa() = tempoPausaCustom
}


//public class RespiracaoConcentracao implements Respirar{
//    private long tempoPausa =4;
//
//    @Override//metodo defualt da interface vem como padrao
//    public long getTempoPausa() {
//        return tempoPausa;
//    }
//
//    @Override
//    public String getNome() {
//        return "Respiração Box";
//    }
//
//    @Override
//    public long getTempoInspirar() {
//        return 4;
//    }
//
//    @Override
//    public long getTempoExpirar() {
//        return 4;
//    }
//
//    @Override
//    public long getNumeroDeCiclos() {
//        return 5;
//    }
//}
