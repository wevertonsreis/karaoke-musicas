package br.com.karaokemusicas.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.karaokemusicas.R;

public class MusicaViewHolder extends RecyclerView.ViewHolder {

    private TextView codigo;
    private TextView interprete;
    private TextView titulo;
    private TextView inicioLetra;
    private ImageView botaoFavorito;

    public MusicaViewHolder(@NonNull View itemView) {
        super(itemView);
        codigo = (TextView) itemView.findViewById(R.id.codigo);
        interprete = (TextView) itemView.findViewById(R.id.interprete);
        titulo = (TextView) itemView.findViewById(R.id.titulo);
        inicioLetra = (TextView) itemView.findViewById(R.id.inicio_letra);
        botaoFavorito = (ImageView) itemView.findViewById(R.id.botao_favorito);
    }

    public TextView getCodigo() {
        return codigo;
    }

    public TextView getInterprete() {
        return interprete;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public TextView getInicioLetra() {
        return inicioLetra;
    }

    public ImageView getBotaoFavorito() {
        return botaoFavorito;
    }
}
