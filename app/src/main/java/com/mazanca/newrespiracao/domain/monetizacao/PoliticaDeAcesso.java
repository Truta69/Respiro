package com.mazanca.newrespiracao.domain.monetizacao;

/**
 * politica de monetizacao
 * se mudar, muda so aqui..
 */
public class PoliticaDeAcesso {
    public BeneficiosDoPlano beneficios(PlanoDoUsuario plano) {
        return switch (plano) {
            case GRATUITO -> new BeneficiosDoPlano(false, false, false);
            case PREMIUM -> new BeneficiosDoPlano(true, true, true);
        };
    }
}
