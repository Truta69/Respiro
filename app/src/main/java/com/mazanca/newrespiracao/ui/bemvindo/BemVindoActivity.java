package com.mazanca.newrespiracao.ui.bemvindo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.core.util.TransicaoDeTelas;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;
import com.mazanca.newrespiracao.ui.principal.PrincipalActivity;

public class BemVindoActivity extends AppCompatActivity {
    private ActivityBemVindoBinding binding;
    private long tempoDeAnimacao = 4000;
    private BemVindoConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityBemVindoBinding::inflate);
        this.config = new BemVindoConfig(getLifecycle(), binding);
        configurarTela();
        iniciarTransicao();
    }

    private void configurarTela() {
        config.configurarTelaBemvindo();
    }

    private void iniciarTransicao() {
        binding.getRoot().postDelayed(() -> {
            Intent intent = new Intent(this, PrincipalActivity.class);
            TransicaoDeTelas.transitar(this,intent,R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }, tempoDeAnimacao);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}