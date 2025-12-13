package com.mazanca.newrespiracao.ui.bemvindo;

import android.graphics.Color;

import androidx.lifecycle.Lifecycle;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.animation.GerenciarCicloVidaAnimacao;
import com.mazanca.newrespiracao.core.util.Cumprimento;
import com.mazanca.newrespiracao.core.util.FrasesMotivacionais;
import com.mazanca.newrespiracao.core.util.GerenciadorDeThemas;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;

import java.time.LocalTime;

public class BemVindoConfig {
    private Lifecycle lifecycle;
    private ActivityBemVindoBinding binding;

    public BemVindoConfig(Lifecycle lifecycle, ActivityBemVindoBinding binding) {
        this.lifecycle = lifecycle;
        this.binding = binding;
    }

    //metodo public para cuidar de tudo
    public void configurarTelaBemvindo() {
        exibirCumprimento();
        configurarAnimacao();
        exibirFraseMotivadora();
    }

    private void exibirCumprimento() {
        String texto = Cumprimento.retornarCumprimento();
        binding.txtSaudacao.setText(texto);
    }

    private void exibirFraseMotivadora() {
        String frase = FrasesMotivacionais.fraseDoDia();
        binding.txtMotivacao.setText(frase);
        int tema = GerenciadorDeThemas.getTemaFinal(LocalTime.now());
        if (tema == R.style.Theme_NewRespiracao_Noite || tema == R.style.Theme_NewRespiracao_Noturno)
            binding.txtMotivacao.setTextColor(Color.WHITE);
    }

    private void configurarAnimacao() {
        GerenciarCicloVidaAnimacao animador = new GerenciarCicloVidaAnimacao(binding.txtSaudacao);
        lifecycle.addObserver(animador);
    }
}
