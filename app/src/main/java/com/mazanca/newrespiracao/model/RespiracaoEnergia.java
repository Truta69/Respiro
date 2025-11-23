package com.mazanca.newrespiracao.model;

public class RespiracaoEnergia implements Respirar {

    @Override
    public String getNome() {
        return "Respiração Energizante";
    }

    @Override
    public long getTempoInspirar() {
        return 2;
    }

    @Override
    public long getTempoExpirar() {
        return 2;
    }

    @Override
    public long getNumeroDeCiclos() {
        return 5;
    }
}
