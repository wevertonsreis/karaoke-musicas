package br.com.karaokemusicas.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.listener.EscutarNoSpotifyListener;
import br.com.karaokemusicas.listener.AssistirNoYouTubeListener;
import br.com.karaokemusicas.dao.MusicaFavoritaDAO;
import br.com.karaokemusicas.modelo.Musica;
import br.com.karaokemusicas.modelo.MusicaFavorita;

public class OpcoesBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = OpcoesBottomSheetFragment.class.getName();

    private Musica musica;
    private Context context;

    public OpcoesBottomSheetFragment(Musica musica, Context context) {
        this.musica = musica;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opcoes_bottom_sheet_content, container, false);

        TextView opcaoAdicionarFavorita = view.findViewById(R.id.menu_botao_adicionar_favorita);
        if(musica.getFavorita()){
            opcaoAdicionarFavorita.setText(R.string.menu_opcao_remover_favorita);
            opcaoAdicionarFavorita.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_clear_24, 0, 0, 0);
        } else {
            opcaoAdicionarFavorita.setText(R.string.menu_opcao_adicionar_favorita);
            opcaoAdicionarFavorita.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_add_24, 0, 0, 0);
        }
        opcaoAdicionarFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicaFavoritaDAO musicaFavoritaDAO = new MusicaFavoritaDAO(context);
                if(musica.getFavorita()){
                    musica.setFavorita(false);
                    musicaFavoritaDAO.deletar(musica);
                    Toast.makeText(context, "Música removida dos favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    musica.setFavorita(true);
                    MusicaFavorita musicaFavorita = new MusicaFavorita();
                    musicaFavorita.setMusica(musica);
                    musicaFavoritaDAO.inserir(musicaFavorita);
                    Toast.makeText(context, "Música incluida dos favoritos", Toast.LENGTH_SHORT).show();
                }
                OpcoesBottomSheetFragment.this.dismiss();
            }
        });

        TextView opcaoVerNoYouTube = view.findViewById(R.id.menu_botao_ver_no_you_tube);
        opcaoVerNoYouTube.setOnClickListener(new AssistirNoYouTubeListener(musica, context, this));

        TextView opcaoEscutarNoSpotify = view.findViewById(R.id.menu_botao_ver_no_spotify);
        opcaoEscutarNoSpotify.setOnClickListener(new EscutarNoSpotifyListener(musica, context, this));

        return view;
    }

}
