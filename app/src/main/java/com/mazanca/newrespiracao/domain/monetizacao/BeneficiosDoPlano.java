package com.mazanca.newrespiracao.domain.monetizacao;

/**
 * o que o plano premite..
 * @param acessoVozGuiada
 * @param acessoTiposAvancados
 * @param acessoEstatisticasAvancadas
 */
public record BeneficiosDoPlano(
        boolean acessoVozGuiada,
        boolean acessoTiposAvancados,
        boolean acessoEstatisticasAvancadas
) {}
