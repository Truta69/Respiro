package com.mazanca.newrespiracao.core.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class FrasesMotivacionais {

    private static List<String> listaFrases = List.of(
            "Na dúvida, valorize sua paz de espirito.",
            "Respire e siga.",
            "Abrace o presente.",
            "A jornada é sua.",
            "Respire..o dia pode esperar.",
            "Respire fundo. Você consegue.",
            "Você é mais forte do que pensa.",
            "Você é capaz.",
            "Seja a mudança.",
            "Seja sua propria luz.",
            "Se você pode sonhar, pode realizar.",
            "Respire fundo. É um novo dia.",
            "Não é o stress que te faz mal, mas sim sua reação a ele.",
            "Silencie a mente, acalme o coração e relaxe o corpo."
    );

    public static String fraseDoDia() {
        LocalDate hoje = LocalDate.now();
        long seed = hoje.getYear() * 10000L
                + hoje.getMonthValue() * 100
                + hoje.getDayOfMonth();
        Random random = new Random(seed);
        int index = random.nextInt(listaFrases.size());
        return listaFrases.get(index);
    }
}
