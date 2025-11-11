package com.mazanca.newrespiracao.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.CountDownTimer;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.animation.AnimarBalao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;

import java.util.Locale;

public class GerenciarSessaoRespiracao {
    private ActivityRespiracaoBinding binding;
    private int ciclosTotais;
    private int tempoInspirar;
    private int tempoExpirar;
    private int tempoPausa;

    private AnimatorSet animadorBalao;
    private CountDownTimer contadorRegressivo;

    private int cicloAtual;
    private boolean exercicioEmAndamento = false;

    public GerenciarSessaoRespiracao(
            ActivityRespiracaoBinding binding,
            int ciclosTotais,
            int tempoInspirar,
            int tempoExpirar,
            int tempoPausa) {
        this.binding = binding;
        this.ciclosTotais = ciclosTotais;
        this.tempoInspirar = tempoInspirar;
        this.tempoExpirar = tempoExpirar;
        this.tempoPausa = tempoPausa;
    }

    public void prepararComponentes() {
        prepararAnimacao();
        long duracaoTotal = (long) (tempoInspirar + tempoExpirar + tempoPausa) * ciclosTotais;
        prepararContador(duracaoTotal);
        resetarParaEstadoInicial();
    }

    public void iniciar() {
        if (exercicioEmAndamento)
            return;
        exercicioEmAndamento = true;
        animadorBalao.start();
        contadorRegressivo.start();
        binding.btnIniciar.setEnabled(false);
        binding.txtInstrucao.setVisibility(View.VISIBLE);
    }

    private void prepararAnimacao() {
        animadorBalao = AnimarBalao.criarCicloDeRespiracao(
                binding.circuloAnimado,
                binding.txtInstrucao,
                tempoInspirar,
                tempoExpirar,
                tempoPausa
        );
        AnimatorListenerAdapter listenerCiclo = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!exercicioEmAndamento) {
                    return;
                }
                cicloAtual++;
                if (cicloAtual < ciclosTotais) {
                    animadorBalao.start();
                } else {
                    finalizarSessao();
                }
            }
        };
        animadorBalao.addListener(listenerCiclo);

    }

    public void resetarParaEstadoInicial() {
        exercicioEmAndamento = false;
        cicloAtual = 0;
        if (contadorRegressivo != null)
            contadorRegressivo.cancel();
        if (animadorBalao != null && animadorBalao.isStarted())
            animadorBalao.cancel();
        binding.toolbarRetornar.setSubtitle(null);
        binding.txtInstrucao.setVisibility(View.INVISIBLE);//???
        binding.circuloAnimado.animate().cancel();
        binding.circuloAnimado.setScaleX(1f);
        binding.circuloAnimado.setScaleY(1f);
        binding.btnIniciar.setEnabled(true);
    }

    private void prepararContador(long duracaoTotal) {
        contadorRegressivo = new CountDownTimer(duracaoTotal * 1000, 1000) {
            @Override
            public void onTick(long millisAteOFim) {
                long segundosRestantes = millisAteOFim / 1000;
                long minutos = segundosRestantes / 60;
                long segundos = segundosRestantes % 60;
                String tempoFormatado = String.format(Locale.getDefault(),
                        "%02d:%02d",
                        minutos,
                        segundos);
                binding.toolbarRetornar.setSubtitle(tempoFormatado);
            }

            @Override
            public void onFinish() {
                if (exercicioEmAndamento) {
                    finalizarSessao();
                }
            }
        };
    }

    public void liberarRecursos() {
        if (animadorBalao != null) {
            animadorBalao.removeAllListeners();
            animadorBalao.cancel();
        }
        if (contadorRegressivo != null) {
            contadorRegressivo.cancel();
        }
    }

    private void finalizarSessao() {
        binding.txtInstrucao.setText(R.string.sessao_finalizada);
        resetarParaEstadoInicial();
    }
}
