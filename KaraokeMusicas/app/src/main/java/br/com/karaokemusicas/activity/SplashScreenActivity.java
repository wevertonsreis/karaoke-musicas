package br.com.karaokemusicas.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;

import br.com.karaokemusicas.R;

/**
 * Controla as acoes do layout activity_splash_screen
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * A tempo que a tela ficara visivel ao usuario e milesegundos
     */
    private static final int TEMPO_APRESENTACAO = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarTelaListaDeMusicas();
            }
        }, TEMPO_APRESENTACAO);
    }

    /**
     * Redireciona o usuario para a tela de lista de musicas e finaliza a tela de apresentacao
     */
    private void mostrarTelaListaDeMusicas() {
        Intent intentTelaListaDeMusicas = new Intent(SplashScreenActivity.this, ListaMusicasActivity.class);
        startActivity(intentTelaListaDeMusicas);
        finish();
    }

}
