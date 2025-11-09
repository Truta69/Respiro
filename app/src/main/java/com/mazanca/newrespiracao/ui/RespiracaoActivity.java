package com.mazanca.newrespiracao.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.animation.AnimarBalao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.util.Constantes;
import com.mazanca.newrespiracao.util.GerarTelaUtil;
import com.mazanca.newrespiracao.util.GerenciadorDeThemas;

import java.util.Locale;

public class RespiracaoActivity extends AppCompatActivity {
    private ActivityRespiracaoBinding binding;
    private AnimatorSet animadorDoBalao; // Variável de membro para guardar nosso animador
    private CountDownTimer contadorRegressivo;
    // Variáveis que a "maestra" usa para controlar a orquestra
    private int ciclosTotais;
    private int cicloAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTelaRespiracao(this);
        int tempoFase = receberParametros();
        binding.toolbarRetornar.setNavigationOnClickListener(v -> finish());

        long duracaoTotal = tempoFase * 2 * ciclosTotais;

        animadorDoBalao = AnimarBalao.criarCicloDeRespiracao(binding.circuloAnimado, binding.txtInstrucao, tempoFase);
        // 3. ADICIONA O CONTADOR DE CICLOS (A LÓGICA PRINCIPAL)
        animadorDoBalao.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cicloAtual++;
                if (cicloAtual < ciclosTotais) {
                    animadorDoBalao.start();
                } else {
                    finalizarSessao();
                }
            }
        });
        //prepara contador
        contadorRegressivo = new CountDownTimer(duracaoTotal * 1000, 1000) {
            @Override
            public void onTick(long millisAteOFim) {
                long segundosRestantes = millisAteOFim / 1000;
                long minutos = segundosRestantes / 60;
                long segundos = segundosRestantes % 60;
                String tempoFormatado = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos);
                //atualiza subtitulo toolbar
                binding.toolbarRetornar.setSubtitle(tempoFormatado);
            }

            @Override
            public void onFinish() {
            }
        };
        iniciarExercicio();
    }

    private void iniciarExercicio() {
        binding.btnIniciar.setOnClickListener(v -> {
            animadorDoBalao.start();
            contadorRegressivo.start();
            v.setEnabled(false);
            //deixa o texto visivel
            binding.txtInstrucao.setVisibility(View.VISIBLE);
        });
    }

    private int receberParametros() {
        Intent intent = getIntent();
        int tempoFase = intent.getIntExtra(Constantes.TEMPO_SEGUNDOS, 4);
        ciclosTotais = intent.getIntExtra(Constantes.NUM_CICLOS, 12);
        String nomeExercicio = intent.getStringExtra(Constantes.NOME_EXERCICIO);
        binding.toolbarRetornar.setTitle(nomeExercicio);
        return tempoFase;
    }

    private void finalizarSessao() {
        binding.txtInstrucao.setText("Acabou..");
        binding.circuloAnimado.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
        binding.btnIniciar.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Limpeza crucial para o novo sistema
        if (animadorDoBalao != null) {
            animadorDoBalao.removeAllListeners();
            animadorDoBalao.cancel();
        }
        if (contadorRegressivo != null) {
            contadorRegressivo.cancel();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
