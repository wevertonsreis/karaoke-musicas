package br.com.karaokemusicas.adapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.dao.MusicaFavoritaDAO;
import br.com.karaokemusicas.holder.MusicaViewHolder;
import br.com.karaokemusicas.modelo.Musica;
import br.com.karaokemusicas.modelo.MusicaFavorita;

public class MusicaAdapter extends RecyclerView.Adapter {

    private List<Musica> musicas;
    private Context context;

    public MusicaAdapter(List<Musica> musicas, Context context) {
        this.musicas = musicas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_musica, parent, false);
        MusicaViewHolder musicaViewHolder = new MusicaViewHolder(view, context);
        return musicaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MusicaViewHolder musicaViewHolder = (MusicaViewHolder) holder;

        final Musica musica = musicas.get(position);
        musicaViewHolder.getCodigo().setText(musica.getId().toString());
        musicaViewHolder.getInterprete().setText(musica.getInterprete());
        musicaViewHolder.getTitulo().setText(musica.getTitulo());
        musicaViewHolder.getInicioLetra().setText(musica.getInicioLetra());

        ImageView botaoFavorito = musicaViewHolder.getBotaoFavorito();

        if(musica.getFavorita()){
            botaoFavorito.setImageIcon(Icon.createWithResource(context, android.R.drawable.btn_star_big_on));
        } else {
            botaoFavorito.setImageIcon(Icon.createWithResource(context, android.R.drawable.btn_star_big_off));
        }
        botaoFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                MusicaFavoritaDAO musicaFavoritaDAO = new MusicaFavoritaDAO(context);
                if(musica.getFavorita()){
                    imageView.setImageIcon(Icon.createWithResource(context, android.R.drawable.btn_star_big_off));
                    musica.setFavorita(false);

                    musicaFavoritaDAO.deletar(musica);
                } else {
                    imageView.setImageIcon(Icon.createWithResource(context, android.R.drawable.btn_star_big_on));
                    musica.setFavorita(true);

                    MusicaFavorita musicaFavorita = new MusicaFavorita();
                    musicaFavorita.setMusica(musica);
                    musicaFavoritaDAO.inserir(musicaFavorita);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return musicas.size();
    }

    public void setFiltro(List<Musica> musicas) {
        this.musicas = new ArrayList<>();
        this.musicas.addAll(musicas);
        notifyDataSetChanged();
    }

}
