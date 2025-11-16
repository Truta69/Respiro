package com.mazanca.newrespiracao.audio;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class NarradorRespiracao {
    private TextToSpeech fala;
    private boolean estaOK = false;

    public NarradorRespiracao(Context context) {
        fala = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                fala.setLanguage(new Locale("pt", "BR"));
                fala.setSpeechRate(0.85F);
                fala.setPitch(1F);
                estaOK = true;
            }
        });
    }

    public void falar(String texto) {
        if (!estaOK) return;
        fala.speak(texto, TextToSpeech.QUEUE_FLUSH,null,"ID_FALA");
    }

    public void parar() {
        if (fala != null) {
            fala.stop();
            fala.shutdown();
        }
    }
}
