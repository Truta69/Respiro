package com.mazanca.newrespiracao.model;

public class MiniPauser implements Respirar{
    @Override
    public String getNome() {
        return "Mini Pauser";
    }

    @Override
    public long getTempoInspirar() {
        return 2;
    }

    @Override
    public long getTempoExpirar() {
        return 3;
    }

    @Override
    public long getNumeroDeCiclos() {
        return 1;
    }

    @Override
    public long getTempoPausa() {
        return 1;
    }
}
