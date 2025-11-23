package com.mazanca.newrespiracao.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.databinding.ActivityBemVindoBinding;
import com.mazanca.newrespiracao.ui.config.BemVindoConfigurador;
import com.mazanca.newrespiracao.util.GerarTelaUtil;
import com.mazanca.newrespiracao.util.GerenciadorDeThemas;

public class BemVindoActivity extends AppCompatActivity {

    private ActivityBemVindoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityBemVindoBinding::inflate);
        new BemVindoConfigurador(this, binding).configurarTelaBemvindo();
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_config, menu);
        MenuItem itemModo = menu.findItem(R.id.modo_noturno);
        itemModo.setChecked(GerenciadorDeThemas.isModoNoturnoLigado());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.modo_noturno) {
            boolean estado = !GerenciadorDeThemas.isModoNoturnoLigado();
            GerenciadorDeThemas.modoNoturno(estado);
            item.setChecked(estado);
            if (estado) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            getWindow().setWindowAnimations(android.R.style.Animation_Toast);
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}