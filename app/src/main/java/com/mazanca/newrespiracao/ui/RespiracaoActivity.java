package com.mazanca.newrespiracao.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.controller.GerenciarSessaoRespiracao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.util.GerarTelaUtil;
import com.mazanca.newrespiracao.util.ValoresConstantes;

public class RespiracaoActivity extends AppCompatActivity {
    private ActivityRespiracaoBinding binding;
    private GerenciarSessaoRespiracao gerenciarSessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this,ActivityRespiracaoBinding::inflate);
        // 1. Recebe os parâmetros da Intent
        Intent intent = getIntent();
        String nomeExercicio = intent.getStringExtra(ValoresConstantes.EXTRA_NOME_EXERCICIO);
        int tempoExpirar = intent.getIntExtra(ValoresConstantes.EXTRA_TEMPO_EXPIRAR, 4);
        int tempoInspirar = intent.getIntExtra(ValoresConstantes.EXTRA_TEMPO_INSPIRAR, 4);
        int ciclosTotais = intent.getIntExtra(ValoresConstantes.EXTRA_NUM_CICLOS, 4);
        int tempoPausa = intent.getIntExtra(ValoresConstantes.EXTRA_TEMPO_PAUSA, 0);
        configurarToolbar(nomeExercicio);
        gerenciarSessao = new GerenciarSessaoRespiracao(
                binding, ciclosTotais, tempoInspirar, tempoExpirar, tempoPausa);
        gerenciarSessao.prepararComponentes();
        configurarBtnIniciar();
    }

    private void configurarToolbar(String nomeExercicio) {
        binding.toolbarRetornar.setTitle(nomeExercicio);
        binding.toolbarRetornar.setNavigationOnClickListener(v -> finish());
    }

    private void configurarBtnIniciar() {
        binding.btnIniciar.setOnClickListener(v -> {
            gerenciarSessao.iniciar(); // Delega a ação de iniciar para o gerenciador
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gerenciarSessao != null) {
            gerenciarSessao.liberarRecursos();//delega p gerenciador
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
