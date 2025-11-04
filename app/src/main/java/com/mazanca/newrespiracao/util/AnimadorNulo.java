package com.mazanca.newrespiracao.util;

import android.animation.Animator;

public class AnimadorNulo extends Animator {
    @Override
    public void start() {}

    @Override
    public void cancel() {}

    @Override
    public boolean isStarted() {
        return false;
    }
}
