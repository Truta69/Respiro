package com.mazanca.newrespiracao.util;

import com.mazanca.newrespiracao.core.timer.ContadorSessaoListener;

public class ContadorFake implements ContadorSessaoListener {
    @Override
    public void onTick(String tempoFormatado) {}

    @Override
    public void onSessaoStart() {}

    @Override
    public void onSessaoEnd(boolean fimNatural) {}

    @Override
    public void onFinish() {}
}
