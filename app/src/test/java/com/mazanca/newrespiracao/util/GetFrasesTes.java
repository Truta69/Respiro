package com.mazanca.newrespiracao.util;

import com.mazanca.newrespiracao.core.util.FrasesMotivacionais;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetFrasesTes {
    @Test
    public void frasesTest() {
        String frases1=FrasesMotivacionais.fraseDoDia();
        String frases2=FrasesMotivacionais.fraseDoDia();
        assertEquals(frases1,frases2);
    }
}
