package br.com.karaokemusicas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.karaokemusicas.service.dto.ListaMusicasDTO;
import br.com.karaokemusicas.service.dto.MusicaDTO;

public class DAOHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "KaraokeMusicas";
    private static final int VERSAO_DB = 3;
    private Context context;

    public DAOHelper(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builderQuery = new StringBuilder();

        builderQuery.append("CREATE TABLE Musicas ( ");
        builderQuery.append("   id INTEGER PRIMARY KEY, ");
        builderQuery.append("   interprete VARCHAR(100) NOT NULL, ");
        builderQuery.append("   titulo VARCHAR(100) NOT NULL, ");
        builderQuery.append("   inicioLetra VARCHAR(300) NOT NULL, ");
        builderQuery.append("   idioma VARCHAR(100) NOT NULL, ");
        builderQuery.append("   interpreteNormalizado VARCHAR(100) NOT NULL, ");
        builderQuery.append("   tituloNormalizado VARCHAR(100) NOT NULL, ");
        builderQuery.append("   inicioLetraNormalizado VARCHAR(300) NOT NULL, ");
        builderQuery.append("   idiomaNormalizado VARCHAR(100) NOT NULL, ");
        builderQuery.append("   dataAtualizacao VARCHAR(20) NOT NULL ");
        builderQuery.append("); ");

        db.execSQL(builderQuery.toString());
        builderQuery.delete(0, builderQuery.length());

        builderQuery.append("CREATE TABLE Musicas_Favoritas ( ");
        builderQuery.append("   id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builderQuery.append("   Musica_id INTEGER REFERENCES Musicas (id) ");
        builderQuery.append("); ");

        try {
            db.execSQL(builderQuery.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("fim da criação");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder builderQuery = new StringBuilder();
        builderQuery.append("DROP TABLE IF EXISTS Musicas;");
        db.execSQL(builderQuery.toString());

        builderQuery.delete(0, builderQuery.length());
        builderQuery.append("DROP TABLE IF EXISTS Musicas_Favoritas;");
        db.execSQL(builderQuery.toString());

        onCreate(db);
    }

    /**
     *
     * @param listaMusicasDTO
     */
    public void inserirValoresIniciais(ListaMusicasDTO listaMusicasDTO, LocalDateTime dataAlteracao) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            for (MusicaDTO musicaDTO : listaMusicasDTO.getMusicas()) {
                ContentValues valores = new ContentValues();
                valores.put("id", musicaDTO.getCodigo());
                valores.put("interprete", musicaDTO.getInterprete());
                valores.put("titulo", musicaDTO.getTitulo());
                valores.put("inicioLetra", musicaDTO.getInicioLetra());
                valores.put("idioma", musicaDTO.getIdioma());
                valores.put("interpreteNormalizado", StringUtils.stripAccents(musicaDTO.getInterprete()).toLowerCase());
                valores.put("tituloNormalizado", StringUtils.stripAccents(musicaDTO.getTitulo()).toLowerCase());
                valores.put("inicioLetraNormalizado", StringUtils.stripAccents(musicaDTO.getInicioLetra()).toLowerCase());
                valores.put("idiomaNormalizado", StringUtils.stripAccents(musicaDTO.getIdioma()).toLowerCase());
                valores.put("dataAtualizacao", dataAlteracao.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                long id = db.insertWithOnConflict("Musicas", null, valores, SQLiteDatabase.CONFLICT_IGNORE);
                if (id == -1) {
                    db.update("Musicas", valores, "id=?", new String[] {musicaDTO.getCodigo().toString()});
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}