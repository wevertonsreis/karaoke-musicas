package br.com.karaokemusicas.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.karaokemusicas.modelo.Musica;

public class MusicaDAO {

    private DAOHelper daoHelper;

    public MusicaDAO(Context context) {
        this.daoHelper = new DAOHelper(context);
    }

    public List<Musica> buscarTodas() {
        String sql = "SELECT * FROM Musicas m ORDER BY m.interprete";

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);

        List<Musica> listaDeMusicas = new ArrayList<>();

        while (cursor.moveToNext()) {
            Musica musica = new Musica();
            musica.setId(cursor.getLong(cursor.getColumnIndex("id")));
            musica.setInterprete(cursor.getString(cursor.getColumnIndex("interprete")));
            musica.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            musica.setInicioLetra(cursor.getString(cursor.getColumnIndex("inicioLetra")));
            musica.setIdioma(cursor.getString(cursor.getColumnIndex("idioma")));

            listaDeMusicas.add(musica);
        }

        cursor.close();

        return listaDeMusicas;
    }
}
