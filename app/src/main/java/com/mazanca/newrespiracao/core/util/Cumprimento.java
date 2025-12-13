package com.mazanca.newrespiracao.core.util;

import java.time.LocalTime;

public class Cumprimento {
    private static final String BOM_DIA = "Bom dia ☀\uFE0F";
    private static final String BOA_TARDE = "Boa tarde\uD83C\uDF1C";
    private static final String BOA_NOITE = "Boa noite\uD83D\uDCA4";

    public static String retornarCumprimento() {
        int hora = LocalTime.now().getHour();
        if (hora >= 0 && hora < 12)
            return BOM_DIA;
        else if (hora >= 12 && hora < 18)
            return BOA_TARDE;
        else
            return BOA_NOITE;
    }
}
