package br.com.karaokemusicas.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.dao.MusicaDAO;

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
                new CarregarMusicasTask().execute();
            }
        }, TEMPO_APRESENTACAO);

    }

    private class CarregarMusicasTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MusicaDAO musicaDAO = new MusicaDAO(SplashScreenActivity.this);
            if (musicaDAO.contarTodas() == 0) {
                musicaDAO.inserirValoresIniciais();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intentTelaListaDeMusicas = new Intent(SplashScreenActivity.this, ListaMusicasActivity.class);
            startActivity(intentTelaListaDeMusicas);
            finish();
        }

    }

}
