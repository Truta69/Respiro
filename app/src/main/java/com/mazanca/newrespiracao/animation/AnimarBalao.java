package com.mazanca.newrespiracao.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

public class AnimarBalao {
    private static final float ESCALA_MAX = 3F;
    private static final float ESCALA_MIN = 1F;

    public static AnimatorSet criarCicloDeRespiracao(
            View circulo, TextView txtInstrucao,
            int tempoInspirar, int tempoExpirar, int tempoPausa) {
        long inspirar = tempoInspirar * 1000L;
        long expirar = tempoExpirar * 1000L;
        long pausar = tempoPausa * 1000L;
        // --- PREPARA A ANIMAÇÃO DE INSPIRAÇÃO (CRESCER) ---
        // Usamos PropertyValuesHolder para animar
        // scaleX e scaleY de forma limpa e conjunta.
        ObjectAnimator objetoInflar = ObjectAnimator.ofPropertyValuesHolder(
                circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X, ESCALA_MAX),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, ESCALA_MAX)
        );
        objetoInflar.setDuration(inspirar);
        objetoInflar.setInterpolator(new AccelerateDecelerateInterpolator());
        // Define o texto "Inspire" exatamente quando a fase de inflar começa
        objetoInflar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                txtInstrucao.setText("Inspire...");
            }
        });
        //prepara pausa
        ObjectAnimator objetoPausar = ObjectAnimator.ofPropertyValuesHolder(
                circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X, ESCALA_MAX),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, ESCALA_MAX)
        );
        objetoPausar.setDuration(pausar);
        objetoPausar.setInterpolator(new AccelerateDecelerateInterpolator());
        // Define o texto "pausa" exatamente quando a fase de desinflar começa
        objetoPausar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                txtInstrucao.setText("Pausa...");
            }
        });
        // --- PREPARA A ANIMAÇÃO DE EXPIRAÇÃO (DIMINUIR) ---
        ObjectAnimator objetoDesinflar = ObjectAnimator.ofPropertyValuesHolder(
                circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X, ESCALA_MIN),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, ESCALA_MIN)
        );
        objetoDesinflar.setDuration(expirar);
        objetoDesinflar.setInterpolator(new AccelerateDecelerateInterpolator());
        // Define o texto "Expire" exatamente quando a fase de desinflar começa
        objetoDesinflar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                txtInstrucao.setText("Expire...");
            }
        });
        AnimatorSet cicloUnico = new AnimatorSet();
        cicloUnico.playSequentially(objetoInflar, objetoPausar, objetoDesinflar);
        return cicloUnico;
    }
}
