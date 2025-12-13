package com.mazanca.newrespiracao.ui.principal;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.core.util.GerenciadorDeThemas;
import com.mazanca.newrespiracao.databinding.ActivityPrincipalBinding;

public class PrincipalActivity extends AppCompatActivity {

    private ActivityPrincipalBinding binding;
    private PrincipalConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityPrincipalBinding::inflate);
        config = new PrincipalConfig(this, binding);
        setSupportActionBar(binding.toolbar);
        iniciar();
    }

    private void iniciar() {
        this.config.configurarPrincipal();
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
        if (item.getItemId() == R.id.modo_noturno) {
            boolean estado = !GerenciadorDeThemas.isModoNoturnoLigado();
            GerenciadorDeThemas.modoNoturno(estado);
            item.setChecked(estado);
            if (estado) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.modo_noturno);
        item.setTitle(GerenciadorDeThemas.isModoNoturnoLigado()
                ? "Modo claro"
                : "Modo escuro");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}