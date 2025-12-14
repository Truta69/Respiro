package com.mazanca.newrespiracao.domain.gamificacao;

import com.mazanca.newrespiracao.domain.session.SessaoConcluidaEvento;

/**
 * para registrar o que o usuario ja fez
 */
public class ProgressoDoUsuario {
    private int totalDeSessoes;
    private long tempoTotalEmSegundos;

    public void registraSessao(SessaoConcluidaEvento evento) {
        totalDeSessoes++;
        this.tempoTotalEmSegundos += evento.tempoEmSegundos();
    }

    public int getTotalDeSessoes() {
        return totalDeSessoes;
    }

    public long getTempoTotalEmSegundos() {
        return tempoTotalEmSegundos;
    }
}
