package com.mazanca.newrespiracao.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class AnimarTexto {
    public static AnimatorSet iniciarAnimacaoCumprimento(View texto) {
        ObjectAnimator animacaoX = ObjectAnimator.ofFloat(texto, "scaleX", 1F, 2.3F);
        ObjectAnimator animacaoY = ObjectAnimator.ofFloat(texto, "scaleY", 1F, 1.5F);
        animacaoX.setRepeatCount(1);
        animacaoX.setRepeatMode(ValueAnimator.REVERSE);

        animacaoY.setRepeatCount(1);
        animacaoY.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet animaSet = new AnimatorSet();
        animaSet.playTogether(animacaoX, animacaoY);
        animaSet.setDuration(2000);
        return animaSet;
    }
}
