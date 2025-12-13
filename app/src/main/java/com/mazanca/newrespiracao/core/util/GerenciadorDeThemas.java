package com.mazanca.newrespiracao.core.util;

import com.mazanca.newrespiracao.R;

import java.time.LocalTime;

public class GerenciadorDeThemas {

    private static boolean modoNoturnoLigado = false;
    private static final int HORA_MANHA = 0;
    private static final int HORA_TARDE = 12;
    private static final int HORA_NOITE = 18;

    public static int getThema() {
        return getTemaFinal(LocalTime.now());
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
        if (hora >= HORA_MANHA && hora < HORA_TARDE) {
            return R.style.Theme_NewRespiracao_Manha;
        } else if (hora >= HORA_TARDE && hora < HORA_NOITE) {
            return R.style.Theme_NewRespiracao_Tarde;
        } else {
            return R.style.Theme_NewRespiracao_Noite;
        }
    }
}
