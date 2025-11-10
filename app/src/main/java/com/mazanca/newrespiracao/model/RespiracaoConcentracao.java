package com.mazanca.newrespiracao.model;

public class RespiracaoConcentracao implements Respirar{
    private int pausa=4;

    public int getPausa() {
        return pausa;
    }

    @Override
    public String getNome() {
        return "Respiraçõa Box";
    }

    @Override
    public int getTempoInspirar() {
        return 4;
    }

    @Override
    public int getTempoExpirar() {
        return 4;
    }

    @Override
    public int getNumeroDeCiclos() {
        return 5;
    }
}
