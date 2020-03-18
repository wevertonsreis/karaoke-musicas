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

    public void inserirValoresIniciais() {
        SQLiteDatabase writableDatabase = daoHelper.getWritableDatabase();
        daoHelper.inserirValoresIniciais(writableDatabase);
    }

    public List<Musica> buscarTodas() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    m.id as m_id, ");
        sql.append("    m.interprete as m_interprete, ");
        sql.append("    m.titulo as m_titulo, ");
        sql.append("    m.inicioLetra as m_inicioLetra, ");
        sql.append("    m.idioma as m_idioma, ");
        sql.append("    mf.id as mf_id ");
        sql.append("FROM Musicas m  ");
        sql.append("    LEFT JOIN Musicas_Favoritas mf ON m.id = mf.Musica_id ");
        sql.append("ORDER BY ");
        sql.append("    mf.id desc, ");
        sql.append("    m.interprete ");

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql.toString(), null);

        List<Musica> listaDeMusicas = new ArrayList<>();

        while (cursor.moveToNext()) {
            Musica musica = new Musica();
            musica.setId(cursor.getLong(cursor.getColumnIndex("m_id")));
            musica.setInterprete(cursor.getString(cursor.getColumnIndex("m_interprete")));
            musica.setTitulo(cursor.getString(cursor.getColumnIndex("m_titulo")));
            musica.setInicioLetra(cursor.getString(cursor.getColumnIndex("m_inicioLetra")));
            musica.setIdioma(cursor.getString(cursor.getColumnIndex("m_idioma")));

            if(cursor.getLong(cursor.getColumnIndex("mf_id")) != 0) {
                musica.setFavorita(true);
            } else {
                musica.setFavorita(false);
            }

            listaDeMusicas.add(musica);
        }

        cursor.close();

        return listaDeMusicas;
    }

    public List<Musica> buscarPorTexto(String texto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    m.id as m_id, ");
        sql.append("    m.interprete as m_interprete, ");
        sql.append("    m.titulo as m_titulo, ");
        sql.append("    m.inicioLetra as m_inicioLetra, ");
        sql.append("    m.idioma as m_idioma, ");
        sql.append("    mf.id as mf_id ");
        sql.append("FROM Musicas m  ");
        sql.append("    LEFT JOIN Musicas_Favoritas mf ON m.id = mf.Musica_id ");
        sql.append("WHERE m.interpreteNormalizado LIKE ? ");
        sql.append("OR m.tituloNormalizado LIKE ? ");
        sql.append("OR m.inicioLetraNormalizado LIKE ? ");
        sql.append("ORDER BY ");
        sql.append("    mf.id desc, ");
        sql.append("    m.interprete ");

        String[] args = {
                "%" + texto + "%",
                "%" + texto + "%",
                "%" + texto + "%"
        };

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql.toString(), args);

        List<Musica> listaDeMusicas = new ArrayList<>();

        while (cursor.moveToNext()) {
            Musica musica = new Musica();
            musica.setId(cursor.getLong(cursor.getColumnIndex("m_id")));
            musica.setInterprete(cursor.getString(cursor.getColumnIndex("m_interprete")));
            musica.setTitulo(cursor.getString(cursor.getColumnIndex("m_titulo")));
            musica.setInicioLetra(cursor.getString(cursor.getColumnIndex("m_inicioLetra")));
            musica.setIdioma(cursor.getString(cursor.getColumnIndex("m_idioma")));

            if(cursor.getLong(cursor.getColumnIndex("mf_id")) != 0) {
                musica.setFavorita(true);
            } else {
                musica.setFavorita(false);
            }

            listaDeMusicas.add(musica);
        }

        cursor.close();

        return listaDeMusicas;
    }
}
