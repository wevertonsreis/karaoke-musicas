package br.com.karaokemusicas.holder;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.karaokemusicas.R;

public class MusicaViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    private TextView codigo;
    private TextView interprete;
    private TextView titulo;
    private TextView inicioLetra;
    private ImageView botaoFavorito;
    private Context context;

    public MusicaViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.codigo = (TextView) itemView.findViewById(R.id.codigo);
        this.interprete = (TextView) itemView.findViewById(R.id.interprete);
        this.titulo = (TextView) itemView.findViewById(R.id.titulo);
        this.inicioLetra = (TextView) itemView.findViewById(R.id.inicio_letra);
        this.botaoFavorito = (ImageView) itemView.findViewById(R.id.botao_favorito);
        this.context = context;
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem verNoYouTube = menu.add(Menu.NONE, 1, 1, "Ver no YouTube");
        MenuItem verNoSpotify = menu.add(Menu.NONE, 1, 1, "Ver no Spotify");

        verNoYouTube.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("onMenuItemClick");

                TextView interprete = (TextView) v.findViewById(R.id.interprete);
                TextView titulo = (TextView) v.findViewById(R.id.titulo);

                System.out.println(interprete.getText() +" "+ titulo.getText());

                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/results?search_query="+interprete.getText() +"+"+ titulo.getText()));
                context.startActivity(webIntent);

                return false;
            }
        });

        verNoSpotify.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setAction(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setComponent(new ComponentName(
                        "com.spotify.music",
                        "com.spotify.music.MainActivity"));
                intent.putExtra(SearchManager.QUERY, interprete.getText() +" - "+ titulo.getText());
                context.startActivity(intent);

                return false;
            }
        });

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
