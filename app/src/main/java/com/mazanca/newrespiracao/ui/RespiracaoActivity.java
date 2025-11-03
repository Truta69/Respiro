package com.mazanca.newrespiracao.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.animation.AnimarBalao;
import com.mazanca.newrespiracao.databinding.ActivityRespiracaoBinding;
import com.mazanca.newrespiracao.util.GerenciadorDeThemas;


public class RespiracaoActivity extends AppCompatActivity {
    private ActivityRespiracaoBinding binding;
    private AnimatorSet animadorDoBalao; // Variável de membro para guardar nosso animador

    // Variáveis que a "maestra" usa para controlar a orquestra
    private int ciclosTotais;
    private int cicloAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(GerenciadorDeThemas.getThema());
        super.onCreate(savedInstanceState);
        binding = ActivityRespiracaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        int tempoFase=intent.getIntExtra("TEMPO_FASE",4);
        ciclosTotais=intent.getIntExtra("NUMERO_CICLOS",12);
        String nomeExercicio=intent.getStringExtra("NOME_EXERCICIO");
        binding.toolbarRetornar.setTitle(nomeExercicio);
        binding.toolbarRetornar.setNavigationOnClickListener(v -> finish());
        animadorDoBalao=AnimarBalao.criarCicloDeRespiracao(binding.circuloAnimado,binding.txtInstrucao,tempoFase);
        // 3. ADICIONA O CONTADOR DE CICLOS (A LÓGICA PRINCIPAL)
        animadorDoBalao.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cicloAtual++;
                if(cicloAtual<ciclosTotais){
                    animadorDoBalao.start();
                }else{
                    finalizarSessao();
                }
            }
        });
        animadorDoBalao.start();
    }
    private void finalizarSessao(){
        binding.txtInstrucao.setText("Acabou..");
        binding.circuloAnimado.animate().scaleX(1f).scaleY(1f).setDuration(500).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Limpeza crucial para o novo sistema
        if(animadorDoBalao!=null){
            animadorDoBalao.removeAllListeners();
            animadorDoBalao.cancel();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        // A activity está se tornando visível, então iniciamos a animação.
//        if(animadorDoBalao!=null &&!animadorDoBalao.isStarted()){
//            animadorDoBalao.start();
//        }
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        // A activity não está mais visível. Hora de parar a animação para economizar recursos.
//        if(animadorDoBalao!=null&&animadorDoBalao.isStarted()){
//            animadorDoBalao.cancel();
//        }
//    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
