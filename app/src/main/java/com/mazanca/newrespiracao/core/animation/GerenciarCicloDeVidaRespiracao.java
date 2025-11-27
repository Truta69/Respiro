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

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (gerenciarSessao != null) {
            gerenciarSessao.liberarRecursos();
        }
        if (narrador != null) {
            narrador.parar();
        }
    }
}
