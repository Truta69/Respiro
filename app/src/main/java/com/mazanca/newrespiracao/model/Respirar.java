package com.mazanca.newrespiracao.model;

/**
 * Interface que define um tipo de respiração.
 * Importante: os tempos retornados são em segundos.
 */
public interface Respirar {
    String getNome();
    Long getTempoInspirar();
    long getTempoExpirar();
    long getNumeroDeCiclos();
    default long getTempoPausa(){
        return 0;
    }
}
