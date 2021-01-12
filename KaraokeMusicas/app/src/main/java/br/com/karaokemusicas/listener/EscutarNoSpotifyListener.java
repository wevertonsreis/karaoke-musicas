package br.com.karaokemusicas.listener;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;

import br.com.karaokemusicas.fragment.OpcoesBottomSheetFragment;
import br.com.karaokemusicas.modelo.Musica;

public class EscutarNoSpotifyListener implements View.OnClickListener {

    private Musica musica;
    private Context context;
    private OpcoesBottomSheetFragment opcoesBottomSheetFragment;

    public EscutarNoSpotifyListener(Musica musica, Context context, OpcoesBottomSheetFragment opcoesBottomSheetFragment) {
        this.musica = musica;
        this.context = context;
        this.opcoesBottomSheetFragment = opcoesBottomSheetFragment;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setAction(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        intent.setComponent(new ComponentName(
                "com.spotify.music",
                "com.spotify.music.MainActivity"));
        intent.putExtra(SearchManager.QUERY, musica.getInterprete() +" - "+ musica.getTitulo());
        context.startActivity(intent);
        opcoesBottomSheetFragment.dismiss();
    }

}