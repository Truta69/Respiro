package com.mazanca.newrespiracao.model;

public class RespiracaoEnergia implements Respiracao{
    @Override
    public String getNomeRespiracao() {
        return "Respiração Energizante";
    }

    @Override
    public int getTempoFase() {
        return 3;
    }

    @Override
    public int getNumeroDeCiclos() {
        return 3;
    }
}
