package br.com.karaokemusicas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.adapter.MusicaAdapter;

public class ListaMusicasFavoritasFragment extends Fragment {

    private MusicaAdapter musicaAdapter;

    public ListaMusicasFavoritasFragment(MusicaAdapter musicaAdapter) {
        this.musicaAdapter = musicaAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicas_favoritas, container, false);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_musicas);
        recyclerView.setAdapter(musicaAdapter);
        recyclerView.setLayoutManager(layout);

        return view;
    }

}
