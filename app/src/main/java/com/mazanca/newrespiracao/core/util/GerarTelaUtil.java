package com.mazanca.newrespiracao.core.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.viewbinding.ViewBinding;

import java.util.function.Function;

public class GerarTelaUtil {

    public static <E extends ViewBinding> E configurarTela(
            Activity tela,
            Function<LayoutInflater, E> inflater) {
        tela.setTheme(GerenciadorDeThemas.getThema());
        E binding = inflater.apply(tela.getLayoutInflater());
        configurarBarraDeStatus(tela.getWindow());
        tela.setContentView(binding.getRoot());
        return binding;
    }
    //so executa se nao for null, assim evita nullpointer...
    private static void configurarBarraDeStatus(Window window) {
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(window, window.getDecorView());
        if (insetsController != null)
            insetsController.setAppearanceLightStatusBars(true);
    }
}
