package com.mazanca.newrespiracao.model;

public class RespiracaoConcentracao implements Respirar{
    private long tempoPausa =4;

    @Override//metodo defualt da interface vem como padrao
    public long getTempoPausa() {
        return tempoPausa;
    }

    @Override
    public String getNome() {
        return "Respiração Box";
    }

    @Override
    public long getTempoInspirar() {
        return 4;
    }

    @Override
    public long getTempoExpirar() {
        return 4;
    }

    @Override
    public long getNumeroDeCiclos() {
        return 5;
    }
}
