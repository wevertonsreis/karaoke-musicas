package br.com.karaokemusicas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import br.com.karaokemusicas.R;
import br.com.karaokemusicas.activity.listener.BuscarMusicasListener;
import br.com.karaokemusicas.adapter.MusicaAdapter;
import br.com.karaokemusicas.adapter.SectionsPagerAdapter;
import br.com.karaokemusicas.dao.MusicaDAO;
import br.com.karaokemusicas.modelo.Musica;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewPager;
    private List<Musica> musicas;
    private List<Musica> musicasFavoritas;
    private MusicaAdapter musicaAdapter;
    private MusicaAdapter musicaFavoritaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MusicaDAO musicaDAO = new MusicaDAO(this);

        musicas = musicaDAO.buscarTodas();
        musicaAdapter = new MusicaAdapter(musicas, this);
        musicasFavoritas = musicaDAO.buscarFavoritas();
        musicaFavoritaAdapter = new MusicaAdapter(musicasFavoritas, this);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this, musicaAdapter, musicaFavoritaAdapter);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MusicaDAO musicaDAO = new MusicaDAO(MainActivity.this);
                List<Musica> musicasFiltradas;
                switch (position) {
                    case 0:
                        musicasFiltradas = musicaDAO.buscarTodas();
                        musicaAdapter.setFiltro(musicasFiltradas);
                        break;
                    case 1:
                        musicasFiltradas = musicaDAO.buscarFavoritas();
                        musicaFavoritaAdapter.setFiltro(musicasFiltradas);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem menuItem = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new BuscarMusicasListener(this, musicaAdapter, musicaFavoritaAdapter, tabs));

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                switch (tabs.getSelectedTabPosition()) {
                    case 0:
                        musicaAdapter.setFiltro(musicas);
                        break;
                    case 1:
                        musicaFavoritaAdapter.setFiltro(musicasFavoritas);
                        break;
                }
                return true;
            }
        });

        return true;
    }

}
