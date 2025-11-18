package com.mazanca.newrespiracao.model;

public class RespiracaoConcentracao implements Respirar{
    private int tempoPausa =4;

    @Override//metodo defualt da interface vem como padrao
    public int getTempoPausa() {
        return tempoPausa;
    }

    @Override
    public String getNome() {
        return "Respiração Box";
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
