package com.mazanca.newrespiracao.domain.session;

/**
 * 1ºpasso para gamificacao..
 * guardar um evento que aconteceu..
 * @param tipoRespiracao-->qual respiracao
 * @param tempoEmSegundos--duracao da respiracao
 * @param timestamp--.quando ocorreu
 */
public record SessaoConcluidaEvento(
        String tipoRespiracao,
        long tempoEmSegundos,
        long timestamp) {
}
