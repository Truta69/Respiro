package com.mazanca.newrespiracao.core.timer;

/**
 o estado do botao faz parte do ciclo da respiracao
 nao da logica da respiracao. entao esses dois novos
 metodos cuidam do estado do botao..
 */
public interface ContadorSessaoListener {
    public void onTick(String tempoFormatado);
    public void onSessaoStart();
    public void onSessaoEnd(boolean fimNatural);
    public void onFinish();
}
