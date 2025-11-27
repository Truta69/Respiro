package com.mazanca.newrespiracao.util;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerenciadorDeThemas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.LocalTime;

public class GetTemaTest {
    private LocalTime horaDia;
    private int temaRecebido;

    @Test
    public void getManha10Horas() {
        horaDia = LocalTime.of(10, 0);
        temaRecebido = GerenciadorDeThemas.getTemaFinal(horaDia);
        assertEquals(R.style.Theme_NewRespiracao_Manha, temaRecebido);
    }

    @Test
    public void getTarde12Horas() {
        horaDia = LocalTime.of(12, 0);
        temaRecebido = GerenciadorDeThemas.getTemaFinal(horaDia);
        assertEquals(R.style.Theme_NewRespiracao_Tarde, temaRecebido);
    }

    @Test
    public void getNoite18Horas() {
        horaDia = LocalTime.of(18, 0, 1);
        temaRecebido = GerenciadorDeThemas.getTemaFinal(horaDia);
        assertEquals(R.style.Theme_NewRespiracao_Noite, temaRecebido);
    }
    @Test
    public void getNoite1SHoras() {
        horaDia = LocalTime.of(0, 0, 1);
        temaRecebido = GerenciadorDeThemas.getTemaFinal(horaDia);
        assertEquals(R.style.Theme_NewRespiracao_Manha, temaRecebido);
    }
    @Test
    public void getManhaQuaseTarde() {
        horaDia = LocalTime.of(11, 59, 58);
        temaRecebido = GerenciadorDeThemas.getTemaFinal(horaDia);
        assertEquals(R.style.Theme_NewRespiracao_Manha, temaRecebido);
    }
    @Test
    public void getMeiaNoite() {
        horaDia = LocalTime.of(0, 0);
        temaRecebido = GerenciadorDeThemas.getTemaFinal(horaDia);
        assertEquals(R.style.Theme_NewRespiracao_Manha, temaRecebido);
    }
}