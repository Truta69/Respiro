package com.mazanca.newrespiracao.ui.escolherCards;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.core.util.TransicaoDeTelas;
import com.mazanca.newrespiracao.databinding.ActivityEscolherRespiracaoBinding;

public class EscolherRespiracaoActivity extends AppCompatActivity {
    private ActivityEscolherRespiracaoBinding binding;
    private EscolherCardsConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityEscolherRespiracaoBinding::inflate);
        config = new EscolherCardsConfig(this, binding);
        configurar();
        configurarCliqueToobar();
    }

    private void configurar() {
        config.configurarTelaCards();
    }

    private void configurarCliqueToobar() {
        //binding.toolbarRespiracao.setNavigationOnClickListener(v -> onBackPressed());
        GerarTelaUtil.configurarToolbarVoltar(this, binding.toolbarRespiracao);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        config = null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        TransicaoDeTelas.fecharActivity(this, R.anim.slide_in_left, R.anim.slide_out_right);
    }
}