package com.mazanca.newrespiracao.core.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class AnimarTexto {

    private static final float SCALAX_MAX = 2.3F;
    private static final float SCALAY_MAX = 1.5F;

    public static AnimatorSet iniciarAnimacaoCumprimento(View texto) {
        ObjectAnimator animacaoX = ObjectAnimator.ofFloat(texto, "scaleX", 1F, SCALAX_MAX);
        ObjectAnimator animacaoY = ObjectAnimator.ofFloat(texto, "scaleY", 1F, SCALAY_MAX);
        configurarPulso(animacaoX);

        configurarPulso(animacaoX);
        configurarPulso(animacaoY);

        AnimatorSet animaSet = new AnimatorSet();
        animaSet.playTogether(animacaoX, animacaoY);
        animaSet.setDuration(2000);
        return animaSet;
    }

    private static void configurarPulso(ObjectAnimator animador) {
        animador.setRepeatCount(1);
        animador.setRepeatMode(ValueAnimator.REVERSE);
    }
}
