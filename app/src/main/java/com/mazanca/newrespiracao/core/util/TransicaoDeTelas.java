package com.mazanca.newrespiracao.core.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

public class TransicaoDeTelas {
    public static void transitar(Context contexto, Intent intent, int animEntrada, int animSaida) {
        if (contexto instanceof Activity atividade) {
            var opcoes= ActivityOptions.makeCustomAnimation(atividade,animEntrada,animSaida);
            atividade.startActivity(intent,opcoes.toBundle());
        }else{
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contexto.startActivity(intent);
        }
    }
}
