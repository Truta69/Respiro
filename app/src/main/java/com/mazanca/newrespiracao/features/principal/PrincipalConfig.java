package com.mazanca.newrespiracao.features.principal;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.controller.TipoRespiracaoController;
import com.mazanca.newrespiracao.databinding.ActivityPrincipalBinding;
import com.mazanca.newrespiracao.model.RelaxamentoProfundo;
import com.mazanca.newrespiracao.model.RespiracaoConcentracao;
import com.mazanca.newrespiracao.model.RespiracaoEnergia;

public class PrincipalConfig {
    private AppCompatActivity activity;
    private ActivityPrincipalBinding binding;

    public PrincipalConfig(AppCompatActivity activity, ActivityPrincipalBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    //metodo public para cuidar de tudo
    public void configurarPrincipal() {
        configurarBotoes();
    }

    private void configurarBotoes() {
        new TipoRespiracaoController(activity, binding.btnRelaxar, new RelaxamentoProfundo());
        new TipoRespiracaoController(activity, binding.btnConcentrar, new RespiracaoConcentracao());
        new TipoRespiracaoController(activity, binding.btnEnergizar, new RespiracaoEnergia());
    }
}
