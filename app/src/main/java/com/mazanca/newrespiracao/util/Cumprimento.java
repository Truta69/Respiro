package com.mazanca.newrespiracao.util;

import java.util.Calendar;

public class Cumprimento {
    public static String retornarCumprimento() {
        int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return (hora >= 0 && hora < 12) ? "Bom dia ☀\uFE0F"
                : (hora >= 12 && hora < 18) ? "Boa tarde\uD83C\uDF1C"
                : "Boa noite\uD83D\uDCA4";
    }
}
