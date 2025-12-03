package com.mazanca.newrespiracao.ui.escolherCards;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.databinding.ActivityEscolherRespiracaoBinding;

public class EscolherRespiracaoActivity extends AppCompatActivity {
    private ActivityEscolherRespiracaoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityEscolherRespiracaoBinding::inflate);
        new EscolherCardsConfig(this, binding).configurarTelaCards();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}