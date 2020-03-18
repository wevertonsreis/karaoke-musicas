package br.com.karaokemusicas.activity.listener;

import android.content.Context;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import br.com.karaokemusicas.adapter.MusicaAdapter;
import br.com.karaokemusicas.dao.MusicaDAO;
import br.com.karaokemusicas.modelo.Musica;

public class BuscarMusicasListener implements SearchView.OnQueryTextListener {

    private List<Musica> musicas;
    private MusicaAdapter musicaAdapter;
    private Context context;

    public BuscarMusicasListener(List<Musica> musicas, MusicaAdapter musicaAdapter, Context context) {
        this.musicas = musicas;
        this.musicaAdapter = musicaAdapter;
        this.context = context;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        try {
            List<Musica> musicasFiltradas = filtrar(musicas, newText);
            musicaAdapter.setFiltro(musicasFiltradas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Musica> filtrar(List<Musica> musicas, String texto) {
        try{
            MusicaDAO musicaDAO = new MusicaDAO(context);
            return musicaDAO.buscarPorTexto(texto);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(0);
    }

}
