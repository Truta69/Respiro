package com.mazanca.newrespiracao.core.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

public class TransicaoDeTelas {
    public static void transitar(Context contexto, Intent intent, int animEntrada, int animSaida) {
        if (contexto instanceof Activity atividade) {
            var opcoes = ActivityOptions.makeCustomAnimation(atividade, animEntrada, animSaida);
            atividade.startActivity(intent, opcoes.toBundle());
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contexto.startActivity(intent);
        }
    }

    /**
     * Para sair da activity nao usa intent
     * @param contexto
     * @param animSaida
     * @param animEntrada
     */
    @SuppressWarnings("deprecation")
    public static void fecharActivity(Activity atividade, int animEntrada, int animSaida) {
        atividade.finish();
        atividade.overridePendingTransition(animEntrada,animSaida);
    }
}
