package com.mazanca.newrespiracao.core.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.mazanca.newrespiracao.R;

public class AnimarBalao {
    private static final float ESCALA_MAX = 2F;
    private static final float ESCALA_MIN = 1F;

    public static AnimatorSet criarCicloDeRespiracao(
            View circulo, TextView txt, long tInspirar, long tExpirar, long tPausa) {
        // Inspirar
        ObjectAnimator inspirar = ObjectAnimator.ofPropertyValuesHolder(circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X, ESCALA_MAX),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, ESCALA_MAX));
        configurarFase(inspirar, tInspirar, txt, R.string.inspirar_instrucao);

        // Pausa (Animamos o Alpha de 1 para 1 apenas para servir de timer, sem processar escala)
        ObjectAnimator pausar = ObjectAnimator.ofFloat(circulo, View.ALPHA, ESCALA_MAX, ESCALA_MAX);
        configurarFase(pausar, tPausa, txt, R.string.segure_instrucao);
        // Expirar
        ObjectAnimator expirar = ObjectAnimator.ofPropertyValuesHolder(circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X, ESCALA_MIN),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, ESCALA_MIN));
        configurarFase(expirar, tExpirar, txt, R.string.expirar_instrucao);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(inspirar, pausar, expirar);
        return set;
    }

    private static void configurarFase(ObjectAnimator anim, long tempo, TextView txt, int resId) {
        anim.setDuration(tempo * 1000L);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        // Listener para trocar o texto da instrução no início da fase
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (txt != null) {
                  txt.setText(resId);
                }
            }
        });
    }
}
