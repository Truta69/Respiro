package com.mazanca.newrespiracao.ui.respiracao;

import android.os.Bundle;

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
    }

    private void inicializaConfiguracao() {
        this.config.configurarTelaRespiracao();//apenas chma o metodo
    }
    private void configurarCliqueToobar(){
       GerarTelaUtil.configurarToolbarVoltar(this, binding.toolbarRetornar);//ver escolherActivity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        config=null;
    }
    //ver outras telas de transicao
    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        TransicaoDeTelas.fecharActivity(this, R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
