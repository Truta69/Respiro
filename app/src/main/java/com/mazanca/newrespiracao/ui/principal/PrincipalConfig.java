package com.mazanca.newrespiracao.ui.principal;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.controller.TipoRespiracaoController;
import com.mazanca.newrespiracao.databinding.ActivityPrincipalBinding;
import com.mazanca.newrespiracao.model.MiniPauser;
import com.mazanca.newrespiracao.model.RelaxamentoProfundo;
import com.mazanca.newrespiracao.model.RespiracaoConcentracao;
import com.mazanca.newrespiracao.model.RespiracaoEnergia;
import com.mazanca.newrespiracao.ui.escolherCards.EscolherRespiracaoActivity;

public class PrincipalConfig {
    private AppCompatActivity activity;
    private ActivityPrincipalBinding binding;

    public PrincipalConfig(AppCompatActivity activity, ActivityPrincipalBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }
    private void configurarNavegacao(){
        binding.cardTop.setOnClickListener(v -> {
            Intent intent=new Intent(activity, EscolherRespiracaoActivity.class);
            activity.startActivity(intent);
        });
    }

    //metodo public para cuidar de tudo
    public void configurarPrincipal() {
        configurarBotoes();
        configurarNavegacao();
    }

    private void configurarBotoes() {
        new TipoRespiracaoController(activity, binding.btnRelaxar, new RelaxamentoProfundo());
        new TipoRespiracaoController(activity, binding.btnConcentrar, new RespiracaoConcentracao());
        new TipoRespiracaoController(activity, binding.btnEnergizar, new RespiracaoEnergia());
    }
}
