package com.mazanca.newrespiracao.ui.escolherCards;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.controller.TipoRespiracaoController;
import com.mazanca.newrespiracao.databinding.ActivityEscolherRespiracaoBinding;
import com.mazanca.newrespiracao.model.MiniPauser;
import com.mazanca.newrespiracao.model.RelaxamentoBasica;
import com.mazanca.newrespiracao.model.RespiracaoConcentracao;
import com.mazanca.newrespiracao.model.RespiracaoEnergia;
import com.mazanca.newrespiracao.model.RespiracaoProfunda;

/**
 * lembrar de separar a chmamda de em duas config
 * EscolherCardsUIConfig → toolbar, textos, cores
 * EscolherCardsNavConfig → ações de clique
 */
public class EscolherCardsConfig {
    private AppCompatActivity activity;
    private ActivityEscolherRespiracaoBinding binding;

    public EscolherCardsConfig(AppCompatActivity activity, ActivityEscolherRespiracaoBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void configurarTelaCards() {
        configurarCards();
    }

    private void configurarCards() {
        new TipoRespiracaoController(activity, binding.cardRelax, new RelaxamentoBasica());
        new TipoRespiracaoController(activity, binding.cardBox, new RespiracaoConcentracao());
        new TipoRespiracaoController(activity, binding.cardEnergia, new RespiracaoEnergia());
        new TipoRespiracaoController(activity, binding.miniPausa, new MiniPauser());
        new TipoRespiracaoController(activity, binding.cardRespProfundo, new RespiracaoProfunda());
    }
}
