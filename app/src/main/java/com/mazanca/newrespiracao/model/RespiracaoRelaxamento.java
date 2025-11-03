package com.mazanca.newrespiracao.model;

public class RespiracaoRelaxamento implements Respiracao{
    @Override
    public String getNomeRespiracao() {
        return "Respiracao Equilibrada";
    }

    @Override
    public int getNumeroDeCiclos() {
        return 10;
    }

    @Override
    public int getTempoFase() {
        return 4;
    }
}
