package com.mazanca.newrespiracao.core.util;

import java.time.LocalTime;

public class Cumprimento {
    public static String retornarCumprimento() {
        int hora = LocalTime.now().getHour();
        if (hora >= 0 && hora < 12)
            return "Bom dia ☀\uFE0F";
        else if (hora >= 12 && hora < 18)
            return "Boa tarde\uD83C\uDF1C";
        else
            return "Boa noite\uD83D\uDCA4";
    }
}
