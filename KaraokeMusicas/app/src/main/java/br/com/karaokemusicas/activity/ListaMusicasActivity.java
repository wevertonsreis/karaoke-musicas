package br.com.karaokemusicas.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.activity.listener.BuscarMusicasListener;
import br.com.karaokemusicas.adapter.MusicaAdapter;
import br.com.karaokemusicas.dao.MusicaDAO;
import br.com.karaokemusicas.modelo.Musica;

public class ListaMusicasActivity extends AppCompatActivity {

    private List<Musica> musicas;

    private MusicaAdapter musicaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musicas);

        MusicaDAO musicaDAO = new MusicaDAO(this);
        musicas = musicaDAO.buscarTodas();
        musicaAdapter = new MusicaAdapter(musicas, this);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView recyclerView = findViewById(R.id.recycler_musicas);
        recyclerView.setAdapter(musicaAdapter);
        recyclerView.setLayoutManager(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem menuItem = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new BuscarMusicasListener(musicas, musicaAdapter, this));

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                musicaAdapter.setFiltro(musicas);
                return true;
            }
        });

        return true;
    }

}
