package br.com.karaokemusicas.listener;

import android.content.Context;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import br.com.karaokemusicas.adapter.MusicaAdapter;
import br.com.karaokemusicas.dao.MusicaDAO;
import br.com.karaokemusicas.modelo.Musica;

public class BuscarMusicasListener implements SearchView.OnQueryTextListener {

    private Context context;
    private MusicaAdapter musicaAdapter;
    private MusicaAdapter musicaFavoritaAdapter;
    private TabLayout tabs;

    public BuscarMusicasListener(Context context, MusicaAdapter musicaAdapter, MusicaAdapter musicaFavoritaAdapter, TabLayout tabs) {
        this.context = context;
        this.musicaAdapter = musicaAdapter;
        this.musicaFavoritaAdapter = musicaFavoritaAdapter;
        this.tabs = tabs;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        MusicaDAO musicaDAO = new MusicaDAO(context);
        List<Musica> musicasFiltradas;
        switch (tabs.getSelectedTabPosition()) {
            case 0:
                musicasFiltradas = musicaDAO.buscarPorTexto(newText);
                musicaAdapter.setFiltro(musicasFiltradas);
                break;
            case 1:
                musicasFiltradas = musicaDAO.buscarFavoritasPorTexto(newText);
                musicaFavoritaAdapter.setFiltro(musicasFiltradas);
                break;
        }
        return false;
    }

}
