package com.mazanca.newrespiracao.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.widget.TextView;

public class GerenciarTextoAnimacao {
    private Animator animatorTexto;
    private boolean jaIniciou = false;

    public Animator iniciaAnimacaoTexto(TextView txtView) {
        animatorTexto = AnimarTexto.iniciarAnimacaoCumprimento(txtView);
        if (animatorTexto == null) {
            animatorTexto = new ValueAnimator();
            ((ValueAnimator) animatorTexto).setDuration(0);
        }
        return animatorTexto;
    }

    public void iniciarSeNecessario() {
        if (animatorTexto != null && !jaIniciou) {
            animatorTexto.start();
            jaIniciou = true;
        }
    }

    public void pausar() {
        if (animatorTexto != null && animatorTexto.isStarted()) {
            animatorTexto.cancel();
        }
    }
//cancela animacao definitivamnete e zera refencias.chamada ondestry da classe BemvidoActivity
    public void liberarRecursos() {
        if (animatorTexto != null) {
            animatorTexto.cancel();
            animatorTexto = null;
        }
        jaIniciou = false;
    }
}
