package com.mazanca.newrespiracao.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.animation.AnimarBalao;
import com.mazanca.newrespiracao.core.audio.NarradorRespiracao;
import com.mazanca.newrespiracao.core.timer.ContadorSessaoListener;
import com.mazanca.newrespiracao.core.timer.GerenciadorContadorSessao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;

public class GerenciarSessaoRespiracao implements ContadorSessaoListener {
    private ActivityRespiracaoBinding binding;
    private long ciclosTotais;
    private long tempoInspirar;
    private long tempoExpirar;
    private long tempoPausa;

    private AnimatorSet animadorBalao;
    private GerenciadorContadorSessao contador;

    private long cicloAtual;
    private boolean exercicioEmAndamento = false;
    private NarradorRespiracao narrador;

    public GerenciarSessaoRespiracao(
            ActivityRespiracaoBinding binding,
            long ciclosTotais,
            long tempoInspirar,
            long tempoExpirar,
            long tempoPausa,
            NarradorRespiracao narrador) {
        this.binding = binding;
        this.ciclosTotais = ciclosTotais;
        this.tempoInspirar = tempoInspirar;
        this.tempoExpirar = tempoExpirar;
        this.tempoPausa = tempoPausa;
        this.narrador = narrador;
    }

    public void prepararComponentes() {
        prepararAnimacao();
        long duracaoTotal = (tempoInspirar + tempoExpirar + tempoPausa) * ciclosTotais;
        prepararContador(duracaoTotal);
   }
    private void setBotaoIniciarHabilitado(boolean habilitado){
        binding.btnIniciar.setEnabled(habilitado);
    }

    public void iniciar() {
        if (exercicioEmAndamento) return;
        exercicioEmAndamento = true;
        //narrador.falar(binding.txtInstrucao.getText().toString());
        contador.iniciar();
        animadorBalao.start();
        binding.txtInstrucao.setVisibility(View.VISIBLE);
    }

    //animatoset no lugar de animarbalao
    private void prepararAnimacao() {
        animadorBalao = AnimarBalao.criarCicloDeRespiracao(
                binding.circuloAnimado,
                binding.txtInstrucao,
                tempoInspirar,
                tempoExpirar,
                tempoPausa,
                narrador
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
        if (contador != null)
            contador.cancelar();
        if (animadorBalao != null && animadorBalao.isStarted())
            animadorBalao.cancel();
        binding.toolbarRetornar.setSubtitle(null);
        binding.circuloAnimado.animate().cancel();
        binding.circuloAnimado.setScaleX(1f);
        binding.circuloAnimado.setScaleY(1f);
    }

    private void prepararContador(long duracao) {
        this.contador = new GerenciadorContadorSessao(duracao, this);
    }

    @Override
    public void onTick(String tempoFormatado) {
        binding.toolbarRetornar.setSubtitle(tempoFormatado);
    }

    @Override
    public void onFinish() {
        if (exercicioEmAndamento) {
            finalizarSessao();
        }
    }

    @Override
    public void onSessaoStart() {
        setBotaoIniciarHabilitado(false);
    }

    @Override
    public void onSessaoEnd(boolean fimNatural) {
        setBotaoIniciarHabilitado(true);
    }

    public void liberarRecursos() {
        if (animadorBalao != null) {
            animadorBalao.removeAllListeners();
            animadorBalao.cancel();
        }
        if (contador != null) {
            contador.cancelar();
        }
    }

    private void finalizarSessao() {
        binding.txtInstrucao.setText(R.string.sessao_finalizada);
        narrador.falar(binding.txtInstrucao.getText().toString());
        resetarParaEstadoInicial();
    }
}
