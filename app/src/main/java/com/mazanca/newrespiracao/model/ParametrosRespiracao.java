package com.mazanca.newrespiracao.model;
/**
 * Record para armazenar os parâmetros de uma sessão de respiração.
 * Substitui a classe tradicional, gerando automaticamente construtor,
 * métodos de acesso (nomeExercicio(), tempoInspirar(), etc.),
 * equals(), hashCode() e toString().
 * DEpois quando chama o costurtor ele associa pela posicao e tipo
 * o record gera construtor padrao e esse construtor auxiliar faz um mapeamento..
 */
public record ParametrosRespiracao(
        String nomeExercicio,
        long tempoInspirar,
        long tempoExpirar,
        long numeroDeCiclos,
        long tempoPausa
) {
    public ParametrosRespiracao(Respirar r){
        this(r.getNome(),r.getTempoInspirar(),r.getTempoExpirar(),r.getNumeroDeCiclos(),r.getTempoPausa());
    }
}

