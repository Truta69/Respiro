package com.mazanca.newrespiracao.core.timer;

import android.os.CountDownTimer;

import java.util.Locale;

public class GerenciadorContadorSessao {
    private CountDownTimer contadorRegressivo;
    // Dependência da interface que criamos
    private final ContadorSessaoListener listener;
    private final long duracaoTotalMillis;

    public GerenciadorContadorSessao(long duracaoTotalSegundos, ContadorSessaoListener listener) {
        this.duracaoTotalMillis = duracaoTotalSegundos * 1000;
        this.listener = listener;
        this.prepararContador();
    }

    private void prepararContador() {
        contadorRegressivo = new CountDownTimer(duracaoTotalMillis, 1000) {
            @Override
            public void onTick(long millisAteOFim) {
                listener.onTick(formatarTempo(millisAteOFim));
            }

            @Override
            public void onFinish() {
                listener.onFinish();
                listener.onSessaoEnd(true);
            }
        };
    }

    private String formatarTempo(long millisRestantes) {
        long segundosRestantes = millisRestantes / 1000;
        long minutos = segundosRestantes / 60;
        long segundos = segundosRestantes % 60;
        return String.format(Locale.getDefault(),
                "%02d:%02d", minutos, segundos);
    }

    /**
     * listener agora desabilita o botao
     */
    public void iniciar() {
        if (contadorRegressivo != null)
            listener.onSessaoStart();
            contadorRegressivo.start();
    }

    public void cancelar() {
        if (contadorRegressivo != null) {
            contadorRegressivo.cancel();
            listener.onSessaoEnd(false);
            contadorRegressivo = null;
        }
    }
}
