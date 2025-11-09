package com.mazanca.newrespiracao.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.model.Respiracao;
import com.mazanca.newrespiracao.ui.BemVindoActivity;
import com.mazanca.newrespiracao.ui.RespiracaoActivity;
import com.mazanca.newrespiracao.util.Constantes;

public class GerenciadorTipoRespiracao {
    private Context contexto;
    private View botao;
    private Respiracao tipoRespiracao;

    public GerenciadorTipoRespiracao(Context contexto, View botao, Respiracao tipoRespiracao) {
        this.contexto = contexto;
        this.botao = botao;
        this.tipoRespiracao = tipoRespiracao;
        configuracao();
    }

    private void configuracao() {
        botao.setOnClickListener(v -> iniciarSessao());
    }
    private void iniciarSessao(){
        Intent intent = new Intent(contexto, RespiracaoActivity.class);
        intent.putExtra(Constantes.NOME_EXERCICIO,tipoRespiracao.getNomeRespiracao());
        intent.putExtra(Constantes.TEMPO_SEGUNDOS,tipoRespiracao.getTempoFase());
        intent.putExtra(Constantes.NUM_CICLOS,tipoRespiracao.getNumeroDeCiclos());
        contexto.startActivity(intent);
        if(contexto instanceof BemVindoActivity){
            ((BemVindoActivity)contexto).overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
    }
}
