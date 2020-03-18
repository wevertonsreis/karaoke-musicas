package br.com.karaokemusicas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.karaokemusicas.modelo.Musica;
import br.com.karaokemusicas.modelo.MusicaFavorita;

public class MusicaFavoritaDAO {

    private DAOHelper daoHelper;

    public MusicaFavoritaDAO(Context context) {
        this.daoHelper = new DAOHelper(context);
    }

    public void inserir(MusicaFavorita musicaFavorita) {
        SQLiteDatabase writableDatabase = daoHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Musica_id", musicaFavorita.getMusica().getId());

        writableDatabase.insert("Musicas_Favoritas", null, values);
    }

    public void deletar(Musica musica) {
        SQLiteDatabase writableDatabase = daoHelper.getWritableDatabase();

        String [] args = { musica.getId().toString()};

        writableDatabase.delete("Musicas_Favoritas", "Musica_id = ?", args);
    }
}
