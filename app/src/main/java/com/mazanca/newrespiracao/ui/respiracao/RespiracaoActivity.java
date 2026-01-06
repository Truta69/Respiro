package com.mazanca.newrespiracao.ui.respiracao;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.core.util.TransicaoDeTelas;
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
        configurarCliqueToobar();
        voltarTelas();
    }

    private void inicializaConfiguracao() {
        this.config.configurarTelaRespiracao();//apenas chma o metodo
    }

    private void configurarCliqueToobar() {
        GerarTelaUtil.configurarToolbarVoltar(this, binding.toolbarRetornar);//ver escolherActivity
    }

    @Override
    protected void onDestroy() {
        if (config != null) {
            config.liberar();
        }
        super.onDestroy();
        binding = null;
        config = null;
    }

    /**
     * Ver a possibiliade de usar esse metodo em uma classe e so fazer a
     * achamada aqui e nas outras telas de transicao..
     */
    private void voltarTelas() {
        OnBackPressedCallback backCallback;
        backCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                TransicaoDeTelas.fecharActivity(RespiracaoActivity.this,
                        R.anim.slide_in_left, R.anim.slide_out_right);
            }
        };
        getOnBackPressedDispatcher().addCallback(this,backCallback);
    }
}
