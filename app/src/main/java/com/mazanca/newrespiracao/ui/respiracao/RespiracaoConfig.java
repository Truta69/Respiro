package com.mazanca.newrespiracao.ui.respiracao;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.controller.GerenciarSessaoRespiracao;
import com.mazanca.newrespiracao.core.animation.GerenciarCicloDeVidaRespiracao;
import com.mazanca.newrespiracao.core.audio.NarradorRespiracao;
import com.mazanca.newrespiracao.core.util.Constantes;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.model.ParametrosRespiracao;
import com.mazanca.newrespiracao.model.Respirar;

public class RespiracaoConfig {
    private final AppCompatActivity activity;//garantir que camos nao mudam
    private final ActivityRespiracaoBinding binding;
    private  GerenciarSessaoRespiracao gerenciarSessao;
    private  NarradorRespiracao narrador;

    public RespiracaoConfig(AppCompatActivity activity, ActivityRespiracaoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void configurarTelaRespiracao() {
        ParametrosRespiracao parametrosRespiracao = obterParametros();
        this.narrador = new NarradorRespiracao(activity);
        iniciarSessao(parametrosRespiracao);
        configurarToolbar(parametrosRespiracao.nomeExercicio());
        configurarBotaoIniciar();
        GerenciarCicloDeVidaRespiracao observer=new GerenciarCicloDeVidaRespiracao(gerenciarSessao,narrador);
        activity.getLifecycle().addObserver(observer);
    }

    private void iniciarSessao(ParametrosRespiracao parametros) {
        this.gerenciarSessao = new GerenciarSessaoRespiracao(
                binding,
                parametros.numeroDeCiclos(),
                parametros.tempoInspirar(),
                parametros.tempoExpirar(),
                parametros.tempoPausa());
        gerenciarSessao.prepararComponentes();
    }

    @SuppressWarnings("deprecation")
    private ParametrosRespiracao obterParametros() {
        Intent intent = activity.getIntent();
        Respirar tipo = (Respirar) intent.getParcelableExtra(Constantes.EXTRA_TIPO_RESPIRACAO);
        return new ParametrosRespiracao(tipo);
    }

    private void configurarToolbar(String nomeExecicio) {
        binding.toolbarRetornar.setTitle(nomeExecicio);
   }

    private void configurarBotaoIniciar() {
        binding.btnIniciar.setOnClickListener(v -> {
            if (gerenciarSessao != null)
                gerenciarSessao.iniciar();
        });
    }
    //quem cria recursoa libera recusos...para chmara em acitivty
    public void liberar(){
        if(gerenciarSessao!=null){
            gerenciarSessao.liberarRecursos();
        }
        if(narrador!=null){
            narrador.parar();
        }
    }
}
