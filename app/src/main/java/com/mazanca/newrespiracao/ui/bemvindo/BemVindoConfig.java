package com.mazanca.newrespiracao.ui.bemvindo;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.core.animation.GerenciarCicloVidaAnimcao;
import com.mazanca.newrespiracao.core.util.Cumprimento;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;

public class BemVindoConfig {
    private AppCompatActivity activity;
    private ActivityBemVindoBinding binding;

    public BemVindoConfig(AppCompatActivity activity, ActivityBemVindoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    //metodo public para cuidar de tudo
    public void configurarTelaBemvindo() {
        exibirCumprimento();
        configurarAnimacao();
    }

    private void exibirCumprimento() {
        String texto = Cumprimento.retornarCumprimento();
        binding.txtSaudacao.setText(texto);
    }

    private void configurarAnimacao() {
        GerenciarCicloVidaAnimcao animador = new GerenciarCicloVidaAnimcao(binding.txtSaudacao);
        activity.getLifecycle().addObserver(animador);
    }
}
