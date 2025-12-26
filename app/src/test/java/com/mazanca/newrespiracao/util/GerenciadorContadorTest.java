package com.mazanca.newrespiracao.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mazanca.newrespiracao.core.timer.GerenciadorContadorSessao;

import org.junit.Test;

public class GerenciadorContadorTest {
    @Test
    public void formatarTempo() {
        GerenciadorContadorSessao g = new GerenciadorContadorSessao(0, new ContadorFake());
        String res = g.formatarTempo(65000);
        assertEquals("01:05", res);
    }
}
