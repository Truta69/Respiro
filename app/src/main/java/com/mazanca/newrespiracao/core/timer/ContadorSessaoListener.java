package com.mazanca.newrespiracao.core.timer;

public interface ContadorSessaoListener {
    public void onTick(String tempoFormatado);
    public void onFinish();
}
