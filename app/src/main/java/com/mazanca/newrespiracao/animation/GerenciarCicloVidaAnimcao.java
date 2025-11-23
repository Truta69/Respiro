package com.mazanca.newrespiracao.animation;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class GerenciarCicloVidaAnimcao implements DefaultLifecycleObserver {
    private GerenciarTextoAnimacao textAnimator;
    private TextView textView;

    public GerenciarCicloVidaAnimcao(TextView textView) {
        this.textAnimator = new GerenciarTextoAnimacao();
        this.textView = textView;
        this.textAnimator.iniciaAnimacaoTexto(this.textView);
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        textAnimator.iniciarSeNecessario();
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        textAnimator.pausar();
        textView.setScaleX(1F);
        textView.setScaleY(1F);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        textAnimator.liberarRecursos();
    }
}
