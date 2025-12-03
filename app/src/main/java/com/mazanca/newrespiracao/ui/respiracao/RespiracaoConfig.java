package com.mazanca.newrespiracao.ui.respiracao;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.controller.GerenciarSessaoRespiracao;
import com.mazanca.newrespiracao.core.animation.GerenciarCicloDeVidaRespiracao;
import com.mazanca.newrespiracao.core.audio.NarradorRespiracao;
import com.mazanca.newrespiracao.core.util.Constantes;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.model.ParametrosRespiracao;

public class RespiracaoConfig {
    private final AppCompatActivity activity;//garantir que camos nao mudam
    private final ActivityRespiracaoBinding binding;
    private GerenciarSessaoRespiracao gerenciarSessao;
    private NarradorRespiracao narrador;

    public RespiracaoConfig(AppCompatActivity activity, ActivityRespiracaoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void configurarTelaRespiracao() {
        ParametrosRespiracao parametros = obterParametros();
        this.narrador = new NarradorRespiracao(activity);
        iniciarSessao(parametros);
        configurarToolbar(parametros.nomeExercicio());
        configurarBtnIniciar();
        activity.getLifecycle().addObserver(new GerenciarCicloDeVidaRespiracao(gerenciarSessao, narrador));
    }

    private void iniciarSessao(ParametrosRespiracao parametros) {
        this.gerenciarSessao = new GerenciarSessaoRespiracao(
                binding,
                parametros.cicloTotais(),
                parametros.tempoInspirar(),
                parametros.tempoInspirar(),
                parametros.tempoPausa(),
                this.narrador);
        gerenciarSessao.prepararComponentes();
    }

    private ParametrosRespiracao obterParametros() {
        Intent intent = activity.getIntent();
        return new ParametrosRespiracao(
                intent.getStringExtra(Constantes.EXTRA_NOME_EXERCICIO),
                intent.getLongExtra(Constantes.EXTRA_TEMPO_EXPIRAR, 4),
                intent.getLongExtra(Constantes.EXTRA_TEMPO_INSPIRAR, 4),
                intent.getLongExtra(Constantes.EXTRA_NUM_CICLOS, 4),
                intent.getLongExtra(Constantes.EXTRA_TEMPO_PAUSA, 0));
    }

    private void configurarToolbar(String nomeExecicio) {
        binding.toolbarRetornar.setTitle(nomeExecicio);
        binding.toolbarRetornar.setNavigationOnClickListener(v -> activity.finish());
    }

    private void configurarBtnIniciar() {
        binding.btnIniciar.setOnClickListener(v -> {
            if (gerenciarSessao != null)
                gerenciarSessao.iniciar();
        });
    }
}
