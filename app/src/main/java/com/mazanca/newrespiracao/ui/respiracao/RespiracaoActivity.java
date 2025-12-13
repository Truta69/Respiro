package com.mazanca.newrespiracao.ui.respiracao;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;

public class RespiracaoActivity extends AppCompatActivity {
    private ActivityRespiracaoBinding binding;
    private RespiracaoConfig config;//injecao

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityRespiracaoBinding::inflate);
        this.config = new RespiracaoConfig(this, binding);//criacao no oncreate
        inicializaConfiguracao();
    }

    private void inicializaConfiguracao() {
        //new RespiracaoConfig(this, binding).configurarTelaRespiracao();
        this.config.configurarTelaRespiracao();//apenas chma o metodo
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
