package com.mazanca.newrespiracao.core.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.viewbinding.ViewBinding;

import java.util.function.Function;

public class GerarTelaUtil {
    /**
     * map e como palavra e sua definicao
     * ou chave e o valor. dados sao acessados pela chave
     * MAP<KEY, VALUE>
     *
     * @param tela
     * @param inflater
     * @param <E>      AQUI UM METODO DE TIPO GENERIC PARA CONFIGURAR AS TELAS
     *                 ACEITA TIPO DE BINDING QUE E A TELAS DO APP
     *                 FUNCTION E UMA REFERENCIA DO INFLAR, ENTAO
     *                 PODE CRIAR O LAUOUT DE QUALQUER TELA
     * @return
     */
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
        if (window == null) return;
        window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        View decorView = window.getDecorView();
        if (decorView == null) return;
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(window, window.getDecorView());
        if (insetsController != null) {
            insetsController.setAppearanceLightStatusBars(true);
            insetsController.setAppearanceLightNavigationBars(true);
        }
    }

    //centralizar o metodo que estava duplicado .precisa enviar os parametros
    //pois e uma classe de fora de respiracao e escolher...
    @SuppressWarnings("deprecation")
    public static void configurarToolbarVoltar(AppCompatActivity activity, androidx.appcompat.widget.Toolbar tollbar) {
        if (tollbar != null) {
            tollbar.setNavigationOnClickListener(v -> activity.onBackPressed());
        }
    }
}
