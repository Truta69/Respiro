package com.mazanca.newrespiracao.ui.config;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.animation.GerenciarCicloDeVidaRespiracao;
import com.mazanca.newrespiracao.audio.NarradorRespiracao;
import com.mazanca.newrespiracao.controller.GerenciarSessaoRespiracao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.model.ParametrosRespiracao;
import com.mazanca.newrespiracao.util.Constantes;

public class RespiracaoConfigurador {
    private AppCompatActivity activity;
    private ActivityRespiracaoBinding binding;
    private GerenciarSessaoRespiracao gerenciarSessao;
    private NarradorRespiracao narrador;

    public RespiracaoConfigurador(AppCompatActivity activity, ActivityRespiracaoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void configurarTelaRespiracao() {
        ParametrosRespiracao parametros = obterParametros();
        this.narrador = new NarradorRespiracao(activity);
        iniciarSessao(parametros);
        configurarToolbar(parametros.getNomeExercicio());
        configurarBtnIniciar();
        activity.getLifecycle().addObserver(new GerenciarCicloDeVidaRespiracao(gerenciarSessao, narrador));
    }

    private void iniciarSessao(ParametrosRespiracao parametros) {
        this.gerenciarSessao = new GerenciarSessaoRespiracao(
                binding,
                parametros.getCicloTotais(),
                parametros.getTempoInspirar(),
                parametros.getTempoExpirar(),
                parametros.getTempoPausa(),
                this.narrador);
        gerenciarSessao.prepararComponentes();
    }

    private ParametrosRespiracao obterParametros() {
        Intent intent = activity.getIntent();
        return new ParametrosRespiracao(
                ((Intent) intent).getStringExtra(Constantes.EXTRA_NOME_EXERCICIO),
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
            if (gerenciarSessao != null) {
                gerenciarSessao.iniciar();
            }
        });
    }
}
