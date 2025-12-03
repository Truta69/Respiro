package com.mazanca.newrespiracao.ui.bemvindo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.FrasesMotivacionais;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.core.util.GerenciadorDeThemas;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;
import com.mazanca.newrespiracao.ui.principal.PrincipalActivity;

import java.time.LocalTime;

public class BemVindoActivity extends AppCompatActivity {
    private ActivityBemVindoBinding binding;
    private long tempoDeAnimacao = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityBemVindoBinding::inflate);
        configurarTela();
        iniciarTransicao();
        carregarFraseMotivacional();
    }

    private void carregarFraseMotivacional() {
        String str = FrasesMotivacionais.fraseDoDia();
        binding.txtMotivacao.setText(str);
        int tema = GerenciadorDeThemas.getTemaFinal(LocalTime.now());
        if (tema == R.style.Theme_NewRespiracao_Noite || tema == R.style.Theme_NewRespiracao_Noturno)
            binding.txtMotivacao.setTextColor(Color.WHITE);
    }

    private void configurarTela() {
        new BemVindoConfig(this, binding).configurarTelaBemvindo();
    }

    private void iniciarTransicao() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }, tempoDeAnimacao);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}