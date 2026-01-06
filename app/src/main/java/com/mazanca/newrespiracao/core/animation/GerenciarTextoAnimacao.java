package com.mazanca.newrespiracao.core.animation;

import android.animation.Animator;
import android.widget.TextView;

public class GerenciarTextoAnimacao {
    private Animator animatorTexto;

    public void iniciaAnimacaoTexto(TextView txtView) {
        animatorTexto = AnimarTexto.iniciarAnimacaoCumprimento(txtView);
   }

    public void iniciarSeNecessario() {
        if (animatorTexto != null && !animatorTexto.isStarted()) {
            animatorTexto.start();
        }
    }

    public void pausar() {
        if (animatorTexto != null && animatorTexto.isRunning()) {
            animatorTexto.cancel();
        }
    }

    //cancela animacao definitivamnete e zera refencias.chamada ondestry da classe BemvidoActivity
    public void liberarRecursos() {
        if (animatorTexto != null) {
            animatorTexto.cancel();
            animatorTexto = null;
        }
    }
}
