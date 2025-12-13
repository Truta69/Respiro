package com.mazanca.newrespiracao.ui.principal;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.TransicaoDeTelas;
import com.mazanca.newrespiracao.databinding.ActivityPrincipalBinding;
import com.mazanca.newrespiracao.ui.escolherCards.EscolherRespiracaoActivity;

public class PrincipalConfig {
    private Activity activity;
    private ActivityPrincipalBinding binding;

    public PrincipalConfig(AppCompatActivity activity, ActivityPrincipalBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    @SuppressWarnings("deprecation")
    private void configurarNavegacao() {
        binding.cardTop.setOnClickListener(v -> {
            Intent intent = new Intent(activity, EscolherRespiracaoActivity.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    //metodo public para cuidar de tudo
    public void configurarPrincipal() {
        configurarNavegacao();
    }
}
