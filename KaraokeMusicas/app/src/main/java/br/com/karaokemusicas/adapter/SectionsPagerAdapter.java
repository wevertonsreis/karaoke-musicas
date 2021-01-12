package br.com.karaokemusicas.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.fragment.ListaMusicasFavoritasFragment;
import br.com.karaokemusicas.fragment.ListaMusicasTodasFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{ R.string.tab_todas, R.string.tab_favoritas };
    private static final int POSICAO_PAGINA_TODAS = 0;
    private static final int POSICAO_PAGINA_FAVORITAS = 1;

    private final Context context;
    private MusicaAdapter musicaAdapter;
    private MusicaAdapter musicaFavoritaAdapter;

    public SectionsPagerAdapter(FragmentManager fm, Context context, MusicaAdapter musicaAdapter, MusicaAdapter musicaFavoritaAdapter) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.musicaAdapter = musicaAdapter;
        this.musicaFavoritaAdapter = musicaFavoritaAdapter;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case POSICAO_PAGINA_TODAS:
                return new ListaMusicasTodasFragment(musicaAdapter);
            case POSICAO_PAGINA_FAVORITAS:
                return new ListaMusicasFavoritasFragment(musicaFavoritaAdapter);
        }
        return new Fragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }

}