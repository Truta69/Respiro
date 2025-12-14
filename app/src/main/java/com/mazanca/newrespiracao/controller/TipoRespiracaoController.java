package com.mazanca.newrespiracao.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.Constantes;
import com.mazanca.newrespiracao.core.util.TransicaoDeTelas;
import com.mazanca.newrespiracao.model.Respirar;
import com.mazanca.newrespiracao.ui.respiracao.RespiracaoActivity;

public class TipoRespiracaoController {
    private Context contexto;
    private Parcelable tipoRespiracao;

    public TipoRespiracaoController(Context contexto, View cardView, Parcelable tipoRespiracao) {
        this.contexto = contexto;
        this.tipoRespiracao = tipoRespiracao;
        cardView.setOnClickListener(v -> iniciarSessao());
    }

    private void iniciarSessao() {
        var intent = criarIntentRespiracao();
        TransicaoDeTelas.transitar(
                contexto,
                intent,
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
    }
    /**
     * passa o objeto ,nao mais campo por campo
     * @return
     */
    private Intent criarIntentRespiracao() {
        var intent = new Intent(contexto, RespiracaoActivity.class);
        intent.putExtra(Constantes.EXTRA_TIPO_RESPIRACAO, tipoRespiracao);
        return intent;
    }
}
