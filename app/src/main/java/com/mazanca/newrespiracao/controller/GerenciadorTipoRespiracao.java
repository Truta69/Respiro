package com.mazanca.newrespiracao.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.model.RelaxamentoProfundo;
import com.mazanca.newrespiracao.model.Respirar;
import com.mazanca.newrespiracao.ui.BemVindoActivity;
import com.mazanca.newrespiracao.ui.RespiracaoActivity;
import com.mazanca.newrespiracao.util.ValoresConstantes;

public class GerenciadorTipoRespiracao {
    private Context contexto;
    private View botao;
    //private Respiracao tipoRespiracao;
    private Respirar tipoRespiracao;
    private RelaxamentoProfundo relaxamentoProfundo;

    public GerenciadorTipoRespiracao(Context contexto, View botao, Respirar tipoRespiracao) {
        this.contexto = contexto;
        this.botao = botao;
        this.tipoRespiracao = tipoRespiracao;
        configuracaoBotao();
    }

    private void configuracaoBotao() {
        botao.setOnClickListener(v -> iniciarSessao(tipoRespiracao));
    }

    private void iniciarSessao(Respirar tipoRespiracao) {
        Intent intent = new Intent(contexto, RespiracaoActivity.class);
        intent.putExtra(ValoresConstantes.EXTRA_NOME_EXERCICIO, tipoRespiracao.getNome());
        intent.putExtra(ValoresConstantes.EXTRA_TEMPO_EXPIRAR, tipoRespiracao.getTempoExpirar());
        intent.putExtra(ValoresConstantes.EXTRA_TEMPO_INSPIRAR, tipoRespiracao.getTempoInspirar());
        intent.putExtra(ValoresConstantes.EXTRA_NUM_CICLOS, tipoRespiracao.getNumeroDeCiclos());
        intent.putExtra(ValoresConstantes.EXTRA_TEMPO_PAUSA,tipoRespiracao.getTempoPausa());

        if(!(contexto instanceof android.app.Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        contexto.startActivity(intent);
        if (contexto instanceof BemVindoActivity) {
            ((BemVindoActivity) contexto).overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
    }
}
