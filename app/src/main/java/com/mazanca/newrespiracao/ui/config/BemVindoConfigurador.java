package com.mazanca.newrespiracao.ui.config;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.animation.GerenciarCicloVidaAnimcao;
import com.mazanca.newrespiracao.controller.GerenciadorTipoRespiracao;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;
import com.mazanca.newrespiracao.model.RelaxamentoProfundo;
import com.mazanca.newrespiracao.model.RespiracaoConcentracao;
import com.mazanca.newrespiracao.model.RespiracaoEnergia;
import com.mazanca.newrespiracao.util.Cumprimento;

public class BemVindoConfigurador {
    private AppCompatActivity activity;
    private ActivityBemVindoBinding binding;

    public BemVindoConfigurador(AppCompatActivity activity, ActivityBemVindoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    //metodo public para cuidar de tudo
    public void configurarTelaBemvindo() {
        exibirCumprimento();
        configurarAnimacao();
        configurarBotoes();
    }

    private void exibirCumprimento() {
        String texto = Cumprimento.retornarCumprimento();
        binding.txtSaudacao.setText(texto);
    }

    private void configurarAnimacao() {
        GerenciarCicloVidaAnimcao animador = new GerenciarCicloVidaAnimcao(binding.txtSaudacao);
        activity.getLifecycle().addObserver(animador);
    }

    private void configurarBotoes() {
        new GerenciadorTipoRespiracao(activity, binding.btnRelaxar, new RelaxamentoProfundo());
        new GerenciadorTipoRespiracao(activity, binding.btnConcentrar, new RespiracaoConcentracao());
        new GerenciadorTipoRespiracao(activity, binding.btnEnergizar, new RespiracaoEnergia());
    }
}
