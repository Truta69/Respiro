package com.mazanca.newrespiracao.audio;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log; // Útil para depuração

import java.util.Locale;

public class NarradorRespiracao {
    private TextToSpeech fala;
    private boolean estaOK = false;

    public NarradorRespiracao(Context context) {
        fala = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                Locale local = new Locale("pt", "BR");
                int res = fala.setLanguage(local);
                if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                    estaOK = false;
                    Log.v("NarradorRespiracao", "Idioma TTS não suportado :" + local);
                } else {
                    fala.setSpeechRate(0.85F);
                    fala.setPitch(1F);
                    estaOK = true;
                }
            } else {
                estaOK = false;
            }
        });
    }

    public boolean isReady() {
        return estaOK && fala != null;
    }

    public void falar(String texto) {
        if (!isReady()) return;
        try {
            fala.speak(texto, TextToSpeech.QUEUE_FLUSH, null, "ID_FALA");
        } catch (Exception e) {
            Log.v("NarradorRespiracao", "Erro de fala :" + e.getMessage());
        }
    }

    public void parar() {
        if (fala != null) {
            try {
                fala.stop();
            }catch (Exception ignorado){}
            try {
                fala.shutdown();
            } catch (Exception ignorado) {}
            fala = null;
            estaOK = false;
        }
    }
}
