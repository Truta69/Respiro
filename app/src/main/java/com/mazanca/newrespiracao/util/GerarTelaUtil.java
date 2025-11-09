package com.mazanca.newrespiracao.util;

import android.app.Activity;

import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;

public class GerarTelaUtil {
    public static ActivityRespiracaoBinding configurarTelaRespiracao(Activity activityResp) {
        activityResp.setTheme(GerenciadorDeThemas.getThema());
        ActivityRespiracaoBinding binding=ActivityRespiracaoBinding.inflate(activityResp.getLayoutInflater());
        activityResp.setContentView(binding.getRoot());
        return binding;
    }
    public static ActivityBemVindoBinding configurarTelaBemVindo(Activity activityBem) {
        activityBem.setTheme(GerenciadorDeThemas.getThema());
        ActivityBemVindoBinding binding=ActivityBemVindoBinding.inflate(activityBem.getLayoutInflater());
        activityBem.setContentView(binding.getRoot());
        return binding;
    }
}
