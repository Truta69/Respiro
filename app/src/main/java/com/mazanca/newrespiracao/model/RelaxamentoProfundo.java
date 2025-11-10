package com.mazanca.newrespiracao.model;

public class RelaxamentoProfundo implements Respirar{
   private int tempoPausa=7;

    public int getTempoPausa() {
        return tempoPausa;
    }

    @Override
    public String getNome() {
        return "Relaxamento profundo";
    }

    @Override
    public int getTempoInspirar() {
        return 4;
    }

    @Override
    public int getTempoExpirar() {
        return 8;
    }

    @Override
    public int getNumeroDeCiclos() {
        return 4;
    }
}
