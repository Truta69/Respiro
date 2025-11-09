package com.mazanca.newrespiracao.model;

public class RespiracaoRelaxamento implements Respiracao{
    @Override
    public String getNomeRespiracao() {
        return "Respiracao Relaxante";
    }

    @Override
    public int getNumeroDeCiclos() {
        return 4;
    }

    @Override
    public int getTempoFase() {
        return 6;
    }
}
