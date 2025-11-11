package com.mazanca.newrespiracao.util;

import com.mazanca.newrespiracao.R;

import java.time.LocalTime;

public class GerenciadorDeThemas {
    public static int getThema() {
        int hora = LocalTime.now().getHour();
        if (hora < 12)
            return R.style.Theme_NewRespiracao_Manha;
        else if (hora < 18)
            return R.style.Theme_NewRespiracao_Tarde;
        else
            return R.style.Theme_NewRespiracao_Noite;
    }
}
