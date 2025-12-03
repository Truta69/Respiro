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
        //estava antes dentro de gerenciarsessaorespiracao
        contadorRegressivo = new CountDownTimer(duracaoTotalMillis, 1000) {
            @Override
            public void onTick(long millisAteOFim) {
                long segundosRestantes = millisAteOFim / 1000;
                long minutos = segundosRestantes / 60;
                long segundos = segundosRestantes % 60;
                String tempoFormatado = String.format(Locale.getDefault(),
                        "%02d:%02d", minutos, segundos);
                // Notifica o Listener (o controlador)
                listener.onTick(tempoFormatado);
            }

            @Override
            public void onFinish() {
                listener.onFinish();
            }
        };
    }

    public void iniciar() {
        if (contadorRegressivo != null)
            contadorRegressivo.start();
    }

    public void cancelar() {
        if (contadorRegressivo != null) {
            contadorRegressivo.cancel();
        }
    }
}
