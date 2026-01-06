package com.mazanca.newrespiracao.core.animation;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.mazanca.newrespiracao.core.audio.NarradorRespiracao;
import com.mazanca.newrespiracao.controller.GerenciarSessaoRespiracao;

public class GerenciarCicloDeVidaRespiracao implements DefaultLifecycleObserver {
    private GerenciarSessaoRespiracao gerenciarSessao;
    private NarradorRespiracao narrador;

    public GerenciarCicloDeVidaRespiracao(GerenciarSessaoRespiracao gerenciarSessao, NarradorRespiracao narrador) {
        this.gerenciarSessao = gerenciarSessao;
        this.narrador = narrador;
    }

    /**
     *  se a activity sair da tela
     *  por ex, tel tocar...mas esta duplicado..entao colocar
     *  em um metodo so
     */
    private void pararTudo(boolean liberar) {
        if (gerenciarSessao != null) {
            gerenciarSessao.resetarParaEstadoInicial();
            if (liberar) {
                gerenciarSessao.liberarRecursos();
            }
        }
        if (narrador != null) {
            narrador.parar();
        }
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        pararTudo(false);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        pararTudo(true);
        gerenciarSessao = null;
        narrador = null;
    }
}
