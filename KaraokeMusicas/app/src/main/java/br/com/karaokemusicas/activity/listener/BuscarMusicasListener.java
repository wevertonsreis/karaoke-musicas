package br.com.karaokemusicas.activity.listener;

import android.widget.SearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import br.com.karaokemusicas.adapter.MusicaAdapter;
import br.com.karaokemusicas.modelo.Musica;

public class BuscarMusicasListener implements SearchView.OnQueryTextListener {

    private List<Musica> musicas;

    private MusicaAdapter musicaAdapter;

    public BuscarMusicasListener(List<Musica> musicas, MusicaAdapter musicaAdapter) {
        this.musicas = musicas;
        this.musicaAdapter = musicaAdapter;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
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
        List<Musica> musicasFiltradas = new ArrayList<>();
        try{
            texto = StringUtils.stripAccents(texto).toLowerCase();

            for (Musica musica : musicas) {
                String interprete = StringUtils.stripAccents(musica.getInterprete()).toLowerCase();
                String titulo = StringUtils.stripAccents(musica.getTitulo()).toLowerCase();
                String inicioLetra = StringUtils.stripAccents(musica.getInicioLetra()).toLowerCase();

                if (interprete.contains(texto) || titulo.contains(texto) || inicioLetra.contains(texto)) {
                    musicasFiltradas.add(musica);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return musicasFiltradas;
    }

}
