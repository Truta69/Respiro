package com.mazanca.newrespiracao.model;

public interface Respirar {
    String getNome();
    int getTempoInspirar();   // ms
    int getTempoExpirar();    // ms
    int getNumeroDeCiclos();
    default int getTempoPausa(){
        return 0;
    }
}
