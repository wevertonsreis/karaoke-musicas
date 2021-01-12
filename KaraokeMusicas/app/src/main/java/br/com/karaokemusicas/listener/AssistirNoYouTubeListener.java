package br.com.karaokemusicas.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import br.com.karaokemusicas.fragment.OpcoesBottomSheetFragment;
import br.com.karaokemusicas.modelo.Musica;

public class AssistirNoYouTubeListener implements View.OnClickListener {

    private static final String URL_YOU_TUBE = "https://www.youtube.com/results?search_query=%s+%s";

    private Musica musica;
    private Context context;
    private OpcoesBottomSheetFragment opcoesBottomSheetFragment;

    public AssistirNoYouTubeListener(Musica musica, Context context, OpcoesBottomSheetFragment opcoesBottomSheetFragment) {
        this.musica = musica;
        this.context = context;
        this.opcoesBottomSheetFragment = opcoesBottomSheetFragment;
    }

    @Override
    public void onClick(View v) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(String.format(URL_YOU_TUBE, musica.getInterprete(), musica.getTitulo())));
        context.startActivity(webIntent);
        opcoesBottomSheetFragment.dismiss();
    }

}
