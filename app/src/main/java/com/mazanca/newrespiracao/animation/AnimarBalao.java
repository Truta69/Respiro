package com.mazanca.newrespiracao.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;

public class AnimarBalao {

    public static AnimatorSet criarCicloDeRespiracao(View circulo,TextView txtInstrucao,int tempoFase){
        long duracaoDaFase = tempoFase * 1000L;
        // --- PREPARA A ANIMAÇÃO DE INSPIRAÇÃO (CRESCER) ---
        // Usamos PropertyValuesHolder para animar
        // scaleX e scaleY de forma limpa e conjunta.
        ObjectAnimator objetoInflar=ObjectAnimator.ofPropertyValuesHolder(
                circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X,3F),
                PropertyValuesHolder.ofFloat(View.SCALE_Y,3F)
        );
        objetoInflar.setDuration(duracaoDaFase);
        objetoInflar.setInterpolator(new AccelerateDecelerateInterpolator());
        // Define o texto "Inspire" exatamente quando a fase de inflar começa
        objetoInflar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                txtInstrucao.setText("Inspire...");
            }
        });
        // --- PREPARA A ANIMAÇÃO DE EXPIRAÇÃO (DIMINUIR) ---
        ObjectAnimator objetoDesinflar=ObjectAnimator.ofPropertyValuesHolder(
                circulo,
                PropertyValuesHolder.ofFloat(View.SCALE_X,1F),
                PropertyValuesHolder.ofFloat(View.SCALE_Y,1F)
        );
        objetoDesinflar.setDuration(duracaoDaFase);
        objetoDesinflar.setInterpolator(new AccelerateDecelerateInterpolator());
        // Define o texto "Expire" exatamente quando a fase de desinflar começa
        objetoDesinflar.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                txtInstrucao.setText("Expire...");
            }
        });
        AnimatorSet cicloUnico = new AnimatorSet();
        cicloUnico.playSequentially(objetoInflar,objetoDesinflar);
        return cicloUnico;
    }

//    public static AnimatorSet iniciarAnimadorBalao(View balao, TextView txtCirculo, int tempoRecebido) {
//        long duracaoInflar = tempoRecebido * 1000L;
//
//        ObjectAnimator animacaoX = ObjectAnimator.ofFloat(balao, "scaleX", 1F, 3F);
//        ObjectAnimator animacaoY = ObjectAnimator.ofFloat(balao, "scaleY", 1F, 3F);
//        animacaoX.setRepeatCount(ValueAnimator.INFINITE);
//        animacaoX.setRepeatMode(ValueAnimator.REVERSE);
//
//        animacaoY.setRepeatCount(ValueAnimator.INFINITE);
//        animacaoY.setRepeatMode(ValueAnimator.REVERSE);
//
//        AnimatorSet animaSet = new AnimatorSet();
//        animaSet.playTogether(animacaoX, animacaoY);
//        animaSet.setDuration(duracaoInflar);
//
//        final boolean[] estaCrescendo = {true};
//        txtCirculo.setText("Inspire");
//        animacaoX.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                estaCrescendo[0] = !estaCrescendo[0];
//                String novoTexto = estaCrescendo[0] ? "Inspire" : "Expire";
//                txtCirculo.setText(novoTexto);
//            }
//        });
//
//        return animaSet;
//    }
}
