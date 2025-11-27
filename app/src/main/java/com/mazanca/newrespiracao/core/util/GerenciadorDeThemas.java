package com.mazanca.newrespiracao.core.util;

import com.mazanca.newrespiracao.R;

import java.time.LocalTime;

public class GerenciadorDeThemas {

    private static boolean modoNoturnoLigado = false;

    public static int getThema() {
        // return getThemaParaHora(LocalTime.now());
        return getTemaFinal(LocalTime.now());
    }

    public static int getThemaParaHora(LocalTime hora) {
        int horaDoDia = hora.getHour();
        if (horaDoDia >= 6 && horaDoDia < 12) {
            return R.style.Theme_NewRespiracao_Manha;
        } else if (horaDoDia >= 12 && horaDoDia < 18) {
            return R.style.Theme_NewRespiracao_Tarde;
        } else {
            return R.style.Theme_NewRespiracao_Noite;
        }
    }

    public static void modoNoturno(boolean escolha) {
        modoNoturnoLigado = escolha;
    }

    public static boolean isModoNoturnoLigado() {
        return modoNoturnoLigado;
    }

    public static int getTemaFinal(LocalTime horaDia) {
        if (modoNoturnoLigado) {
            return R.style.Theme_NewRespiracao_Noturno;
        }
        int hora = horaDia.getHour();
        if (hora >= 0 && hora < 12) {
            return R.style.Theme_NewRespiracao_Manha;
        } else if (hora >= 12 && hora < 18) {
            return R.style.Theme_NewRespiracao_Tarde;
        } else {
            return R.style.Theme_NewRespiracao_Noite;
        }
    }
}
