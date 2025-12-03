package com.mazanca.newrespiracao.ui.escolherCards;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.controller.TipoRespiracaoController;
import com.mazanca.newrespiracao.databinding.ActivityEscolherRespiracaoBinding;
import com.mazanca.newrespiracao.model.MiniPauser;
import com.mazanca.newrespiracao.model.RelaxamentoProfundo;
import com.mazanca.newrespiracao.model.RespiracaoConcentracao;
import com.mazanca.newrespiracao.model.RespiracaoEnergia;

public class EscolherCardsConfig {
    private AppCompatActivity activity;
    private ActivityEscolherRespiracaoBinding binding;

    public EscolherCardsConfig(AppCompatActivity activity, ActivityEscolherRespiracaoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void configurarTelaCards() {
        configurarCards();
        configurarToolbar();
    }

    private void configurarCards() {
        new TipoRespiracaoController(activity, binding.cardRelax, new RelaxamentoProfundo());
        new TipoRespiracaoController(activity, binding.cardBox, new RespiracaoConcentracao());
        new TipoRespiracaoController(activity, binding.cardEnergia, new RespiracaoEnergia());
        new TipoRespiracaoController(activity, binding.miniPausa, new MiniPauser());
    }
    private void configurarToolbar(){
        binding.toolbarRespiracao.setNavigationOnClickListener(v -> activity.finish());
    }
}
