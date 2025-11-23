package com.mazanca.newrespiracao.model;

public class RelaxamentoProfundo implements Respirar {
    private long tempoPausa = 7;

    @Override//metodo default da interface
    public long getTempoPausa() {
        return tempoPausa;
    }

    @Override
    public String getNome() {
        return "Relaxamento profundo";
    }

    @Override
    public long getTempoInspirar() {
        return 4;
    }

    @Override
    public long getTempoExpirar() {
        return 8;
    }

    @Override
    public long getNumeroDeCiclos() {
        return 4;
    }
}
