package com.mazanca.newrespiracao.util;

import com.mazanca.newrespiracao.core.timer.ContadorSessaoListener;

public class ListenerFake implements ContadorSessaoListener {
    boolean iniciou = false;
    boolean encerrou = false;
    Boolean fimNatural = null;

    @Override
    public void onTick(String tempoFormatado) {}

    @Override
    public void onSessaoStart() {
        iniciou=true;
    }

    @Override
    public void onSessaoEnd(boolean fimNatural) {
        encerrou=true;
        this.fimNatural=fimNatural;
    }

    @Override
    public void onFinish() {}
}
