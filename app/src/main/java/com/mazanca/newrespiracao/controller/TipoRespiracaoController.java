package com.mazanca.newrespiracao.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.Constantes;
import com.mazanca.newrespiracao.model.Respirar;
import com.mazanca.newrespiracao.ui.respiracao.RespiracaoActivity;

public class TipoRespiracaoController {
    private Context contexto;
    private Respirar tipoRespiracao;

//    public TipoRespiracaoController(Context contexto, View botao, Respirar tipoRespiracao) {
//        this.contexto = contexto;
//        this.tipoRespiracao = tipoRespiracao;
//        botao.setOnClickListener(v -> iniciarSessao());
//    }
    public TipoRespiracaoController(Context contexto, View cardView, Respirar tipoRespiracao) {
        this.contexto = contexto;
        this.tipoRespiracao = tipoRespiracao;
        cardView.setOnClickListener(v -> iniciarSessao());
    }

    private void iniciarSessao() {
        var intent = criarIntentRespiracao();
        if (!(contexto instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        abrirActivity(intent);
    }

    private Intent criarIntentRespiracao() {
        var intent = new Intent(contexto, RespiracaoActivity.class);
        intent.putExtra(Constantes.EXTRA_NOME_EXERCICIO, tipoRespiracao.getNome());
        intent.putExtra(Constantes.EXTRA_TEMPO_EXPIRAR, tipoRespiracao.getTempoExpirar());
        intent.putExtra(Constantes.EXTRA_TEMPO_INSPIRAR, tipoRespiracao.getTempoInspirar());
        intent.putExtra(Constantes.EXTRA_NUM_CICLOS, tipoRespiracao.getNumeroDeCiclos());
        intent.putExtra(Constantes.EXTRA_TEMPO_PAUSA, tipoRespiracao.getTempoPausa());
        return intent;
    }

    private void abrirActivity(Intent intent) {
        contexto.startActivity(intent);
        if (contexto instanceof Activity atividade) {
            atividade.overridePendingTransition(
                    R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
