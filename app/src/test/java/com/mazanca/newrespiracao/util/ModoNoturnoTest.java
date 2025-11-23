package com.mazanca.newrespiracao.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mazanca.newrespiracao.R;

import java.time.LocalTime;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModoNoturnoTest {

    private GerenciadorDeThemas temas;
    private boolean usuarioEscolheu;

    @Before
    public void iniciarTemas() {
        temas = new GerenciadorDeThemas();
    }

    @Test
    public void modoNoturno() {
        usuarioEscolheu = true;
        temas.modoNoturno(usuarioEscolheu);
        assertTrue(temas.isModoNoturnoLigado());
    }

    @Test
    public void modoNoturnoDesliga() {
        temas.modoNoturno(false);
        assertFalse(temas.isModoNoturnoLigado());
    }

    @Test
    public void modoNoturnoLigado() {
        temas.modoNoturno(true);
        LocalTime hora = LocalTime.of(9, 0);
        int tema = GerenciadorDeThemas.getTemaFinal(hora);
        assertEquals(R.style.Theme_NewRespiracao_Noturno, tema);
    }
    @Test
    public void modoNoturnoDesligado() {
        temas.modoNoturno(false);
        LocalTime hora = LocalTime.of(9, 0);
        int tema = temas.getTemaFinal(hora);
        assertEquals(R.style.Theme_NewRespiracao_Manha, tema);
    }
}