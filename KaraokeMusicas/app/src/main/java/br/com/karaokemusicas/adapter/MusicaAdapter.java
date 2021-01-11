package br.com.karaokemusicas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.fragment.OpcoesBottomSheetFragment;
import br.com.karaokemusicas.holder.MusicaViewHolder;
import br.com.karaokemusicas.modelo.Musica;

public class MusicaAdapter extends RecyclerView.Adapter {

    private List<Musica> musicas;
    private Context context;
    private FragmentManager fragmentManager;

    public MusicaAdapter(List<Musica> musicas,  FragmentManager fragmentManager, Context context) {
        this.musicas = musicas;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_musica, parent, false);
        return new MusicaViewHolder(view);
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

        botaoFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpcoesBottomSheetFragment opcoesBottomSheetFragment = new OpcoesBottomSheetFragment(musica, context);
                opcoesBottomSheetFragment.show(fragmentManager, "teste");
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
