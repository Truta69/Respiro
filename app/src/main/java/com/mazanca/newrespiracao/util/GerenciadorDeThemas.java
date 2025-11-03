package com.mazanca.newrespiracao.util;

import java.util.Calendar;

import com.mazanca.newrespiracao.R;

public class GerenciadorDeThemas {
    public static int getThema() {
        Calendar calendar = Calendar.getInstance();
        int horaDia = calendar.get(Calendar.HOUR_OF_DAY);
        if (horaDia >= 0 && horaDia < 12)
            return R.style.Theme_NewRespiracao_Manha;
        else if (horaDia >= 12 && horaDia < 18)
            return R.style.Theme_NewRespiracao_Tarde;
        else
            return R.style.Theme_NewRespiracao_Noite;
    }
}
