package com.mazanca.newrespiracao.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mazanca.newrespiracao.animation.GerenciarTextoAnimacao;
import com.mazanca.newrespiracao.controller.GerenciadorTipoRespiracao;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;
import com.mazanca.newrespiracao.model.RelaxamentoProfundo;
import com.mazanca.newrespiracao.model.RespiracaoConcentracao;
import com.mazanca.newrespiracao.model.RespiracaoEnergia;
import com.mazanca.newrespiracao.util.Cumprimento;
import com.mazanca.newrespiracao.util.GerarTelaUtil;

public class BemVindoActivity extends AppCompatActivity {

    //refencia pra todas views
    //adicionada imports em build.gradle app viewbinding
    //ai pega referencia das views no lugar de r.id
    private ActivityBemVindoBinding binding;
    //aquela problema de versoes od agp e do sdk
    //ewsolvido com *** implementation("androidx.activity:activity:1.9.0")**
    //tambem em build gradle

    private GerenciarTextoAnimacao gerenciarTextoAnimacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTelaBemVindo(this);
        exibirCumprimento();
        gerenciarTextoAnimacao = new GerenciarTextoAnimacao();
        gerenciarTextoAnimacao.iniciaAnimacaoTexto(binding.txtSaudacao);
        configurarBotoes();
    }

    private void configurarBotoes() {
        new GerenciadorTipoRespiracao(this, binding.btnRelaxar, new RelaxamentoProfundo());
        new GerenciadorTipoRespiracao(this, binding.btnEnergizar, new RespiracaoEnergia());
        new GerenciadorTipoRespiracao(this, binding.btnConcentrar, new RespiracaoConcentracao());
    }

    private void exibirCumprimento() {
        binding.txtSaudacao.setText(Cumprimento.retornarCumprimento());
    }

    @Override
    protected void onStart() {
        super.onStart();
        gerenciarTextoAnimacao.iniciarSeNecessario();
    }

    //cancela ao quase sair da tela para economizar recuros
    @Override
    protected void onPause() {
        super.onPause();
        gerenciarTextoAnimacao.pausar();
        //resentando aview
        binding.txtSaudacao.setScaleX(1.0f);
        binding.txtSaudacao.setScaleY(1.0f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gerenciarTextoAnimacao.liberarRecursos();
        binding = null;
    }
}