package com.mazanca.newrespiracao.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.animation.AnimarBalao;
import com.mazanca.newrespiracao.core.timer.ContadorSessaoListener;
import com.mazanca.newrespiracao.core.timer.GerenciadorContadorSessao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.domain.session.EstadoDaSessao;

public class GerenciarSessaoRespiracao implements ContadorSessaoListener {
    private ActivityRespiracaoBinding binding;
    private final long ciclosTotais;
    private final long tempoInspirar;
    private final long tempoExpirar;
    private final long tempoPausa;

    private AnimatorSet animadorBalao;
    private GerenciadorContadorSessao contador;

    private long cicloAtual;
    private EstadoDaSessao estado = EstadoDaSessao.PARADA;//ENUM

    public GerenciarSessaoRespiracao(
            ActivityRespiracaoBinding binding,
            long ciclosTotais,
            long tempoInspirar,
            long tempoExpirar,
            long tempoPausa
    ) {
        this.binding = binding;
        this.ciclosTotais = ciclosTotais;
        this.tempoInspirar = tempoInspirar;
        this.tempoExpirar = tempoExpirar;
        this.tempoPausa = tempoPausa;
    }

    public void prepararComponentes() {
        prepararAnimacao();
        long duracaoTotal = (tempoInspirar + tempoExpirar + tempoPausa) * ciclosTotais;
        this.contador = new GerenciadorContadorSessao(duracaoTotal, this);
    }

    private void setBotaoIniciarHabilitado(boolean habilitado) {
        if (binding != null)
            binding.btnIniciar.setEnabled(habilitado);
    }

    public void iniciar() {
        if (estado == EstadoDaSessao.EM_ANDAMENTO || binding == null) return;
        estado = EstadoDaSessao.EM_ANDAMENTO;
        binding.circuloAnimado.setLayerType(View.LAYER_TYPE_HARDWARE, null);

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
                tempoPausa
        );
        AnimatorListenerAdapter listenerCiclo = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (estado == EstadoDaSessao.PARADA) {
                    return;
                }
                cicloAtual++;
                if (cicloAtual < ciclosTotais) {
                    animadorBalao.start();
                }
            }
        };
        animadorBalao.addListener(listenerCiclo);
    }

    public void resetarParaEstadoInicial() {
        estado = EstadoDaSessao.PARADA;
        cicloAtual = 0;
        if (contador != null) {
            contador.cancelar();
            long duracaoTotal = (tempoInspirar + tempoExpirar + tempoPausa) * ciclosTotais;
            this.contador = new GerenciadorContadorSessao(duracaoTotal, this);
        }
        if (animadorBalao != null && animadorBalao.isStarted())
            animadorBalao.cancel();
        if (binding != null) {
            binding.toolbarRetornar.setSubtitle(null);
            binding.circuloAnimado.animate().cancel();
            binding.circuloAnimado.setScaleX(1f);
            binding.circuloAnimado.setScaleY(1f);
        }
    }

    @Override
    public void onTick(String tempoFormatado) {
        if (binding != null)
            binding.toolbarRetornar.setSubtitle(tempoFormatado);
    }

    @Override
    public void onFinish() {
        if (estado == EstadoDaSessao.EM_ANDAMENTO) {
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
            animadorBalao = null;
        }
        if (contador != null) {
            contador.cancelar();
            contador = null;
        }
        this.binding = null;
    }

    private void finalizarSessao() {
        estado = EstadoDaSessao.PARADA;
        if (animadorBalao != null) {
            animadorBalao.cancel();
        }
        if (binding != null) {
            binding.txtInstrucao.setText(R.string.sessao_finalizada);
            resetarParaEstadoInicial();
        }
    }
}
