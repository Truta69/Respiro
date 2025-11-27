package com.mazanca.newrespiracao.features.principal;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mazanca.newrespiracao.R;
import com.mazanca.newrespiracao.databinding.ActivityPrincipalBinding;
import com.mazanca.newrespiracao.core.util.GerarTelaUtil;
import com.mazanca.newrespiracao.core.util.GerenciadorDeThemas;

public class PrincipalActivity extends AppCompatActivity {

    private ActivityPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GerarTelaUtil.configurarTela(this, ActivityPrincipalBinding::inflate);
        new PrincipalConfig(this, binding).configurarPrincipal();
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
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.modo_noturno);
        boolean modoEscuroAtivo = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        if (modoEscuroAtivo) {
            item.setTitle("Modo claro");
        } else {
            item.setTitle("Modo escuro");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}