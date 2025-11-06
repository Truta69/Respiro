package com.mazanca.newrespiracao.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.animation.AnimarTexto;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;
import com.mazanca.newrespiracao.model.Respiracao;
import com.mazanca.newrespiracao.model.RespiracaoRelaxamento;
import com.mazanca.newrespiracao.util.Cumprimento;
import com.mazanca.newrespiracao.util.GerenciadorDeThemas;

public class BemVindoActivity extends AppCompatActivity {

    //refencia pra todas views
    //adicionada imports em build.gradle app viewbinding
    //ai pega referencia das views no lugar de r.id
    private ActivityBemVindoBinding binding;
    //aquela problema de versoes od agp e do sdk
    //ewsolvido com *** implementation("androidx.activity:activity:1.9.0")**
    //tambem em build gradle
    private Animator animadorDeBoasVindas; // Variável de membro para o animador
    private boolean animacaoDeBoasVindasJaIniciou = false;
    private Respiracao exercicioEscolhido;

    public static  final String CHAVE_NOME_EXERCICIO="NOME_EXERCICIO";
    public static  final String CHAVE_TEMPO_EXERCICIO="TEMPO_EXERCICIO";
    public static  final String CHAVE_NUM_CICLOS_EXE="NUMERO_CICLOS_EXE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(GerenciadorDeThemas.getThema());
        super.onCreate(savedInstanceState);
        binding = ActivityBemVindoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getCumprimento();
        animarTextoCumprimento();
        respirarBasica();
    }

    private void respirarBasica() {
            exercicioEscolhido= new RespiracaoRelaxamento();
        binding.btnFloating.setOnClickListener(v -> {
            Intent intent = new Intent(BemVindoActivity.this, RespiracaoActivity.class);
            intent.putExtra(CHAVE_NOME_EXERCICIO, exercicioEscolhido.getNomeRespiracao());
            intent.putExtra(CHAVE_TEMPO_EXERCICIO, exercicioEscolhido.getTempoFase());
            intent.putExtra(CHAVE_NUM_CICLOS_EXE, exercicioEscolhido.getNumeroDeCiclos());
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void animarTextoCumprimento() {
        animadorDeBoasVindas = AnimarTexto.iniciarAnimacaoCumprimento(binding.txtSaudacao);
        if(animadorDeBoasVindas==null){
            animadorDeBoasVindas=new ValueAnimator();
            animadorDeBoasVindas.setDuration(0);
        }
    }
    private void getCumprimento() {
        String resposta = Cumprimento.retornarCumprimento();
        binding.txtSaudacao.setText(resposta);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //FLAG CONTTROLA PARA ANIMACAO DE CUMPRIMENTO UMA VEZ
        if (!animacaoDeBoasVindasJaIniciou) {
            animadorDeBoasVindas.start();
            animacaoDeBoasVindasJaIniciou = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // A tela está prestes a ficar invisível. Cancele a animação imediatamente.
        // `onPause` é mais rápido que `onStop`, ideal para interromper a ação o quanto antes.
        if (animadorDeBoasVindas != null && animadorDeBoasVindas.isStarted()) {
            animadorDeBoasVindas.cancel();
        }
        //resentando aview
        binding.txtSaudacao.setScaleX(1.0f);
        binding.txtSaudacao.setScaleY(1.0f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}