package com.mazanca.newrespiracao.model;

/**
 * Interface que define um tipo de respiração.
 * Importante: os tempos retornados são em segundos.
 */
public interface Respirar {
    String getNome();
    int getTempoInspirar();
    int getTempoExpirar();
    int getNumeroDeCiclos();
    default int getTempoPausa(){
        return 0;
    }
}
