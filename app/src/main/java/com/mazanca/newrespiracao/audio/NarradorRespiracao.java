
package com.mazanca.newrespiracao.audio;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class NarradorRespiracao implements TextToSpeech.OnInitListener {

    private TextToSpeech fala;
    private boolean estaOK = false;
    private String falaPendente = null;

    public NarradorRespiracao(Context context) {
        fala = new TextToSpeech(context, this); // <-- usa o onInit daqui
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale local = new Locale("pt", "BR");
            int res = fala.setLanguage(local);
            if (res != TextToSpeech.LANG_MISSING_DATA &&
                    res != TextToSpeech.LANG_NOT_SUPPORTED) {
                fala.setSpeechRate(0.85F);
                fala.setPitch(1F);
                estaOK = true;
                // Se havia fala pendente, fala agora
                if (falaPendente != null) {
                    falar(falaPendente);
                    falaPendente = null;
                }
            } else {
                Log.v("NarradorRespiracao", "Idioma TTS não suportado: " + local);
                estaOK = false;
            }
        }
    }

    public void falar(String texto) {
        if (!isReady()) {
            falaPendente = texto; // Salva para falar depois
            return;
        }

        try {
            fala.speak(texto, TextToSpeech.QUEUE_FLUSH, null, "ID_FALA");
        } catch (Exception e) {
            Log.v("NarradorRespiracao", "Erro de fala: " + e.getMessage());
        }
    }

    public boolean isReady() {
        return estaOK && fala != null;
    }

    public void parar() {
        if (fala != null) {
            try {
                fala.stop();
            } catch (Exception ignorado) {
            }
            try {
                fala.shutdown();
            } catch (Exception ignorado) {
            }
            fala = null;
            estaOK = false;
        }
    }
}
