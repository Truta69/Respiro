package com.mazanca.newrespiracao.util;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.viewbinding.ViewBinding;

import java.util.function.Function;

public class GerarTelaUtil {

    public static <E extends ViewBinding> E configurarTela(
            Activity tela,
            Function<LayoutInflater, E> inflater) {
        tela.setTheme(GerenciadorDeThemas.getThema());
        E binding = inflater.apply(tela.getLayoutInflater());
        tela.setContentView(binding.getRoot());
        return binding;
    }
}
