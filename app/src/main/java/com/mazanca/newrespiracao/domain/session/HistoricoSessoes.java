package com.mazanca.newrespiracao.domain.session;
/**
 * historico de sessoes do usuario
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoSessoes {
    private final List<SessaoConcluidaEvento> eventos = new ArrayList<>();

    /**
     * guarda um evento
     * @param evento
     */
    public void registrar(SessaoConcluidaEvento evento) {
        eventos.add(evento);
    }

    /**
     * somente para ler o historico de eventos
     * @return
     */
    public List<SessaoConcluidaEvento> todos() {
        return Collections.unmodifiableList(eventos);
    }

    public int quantidadeUltimosDias(int dias) {
        long agora = System.currentTimeMillis();
        long limite = agora - (dias * 24L * 60 * 60 * 1000);
        return (int) eventos.stream().
                filter(e -> e.timestamp() >= limite).
                count();
    }
}
