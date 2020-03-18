package br.com.karaokemusicas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DAOHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "KaraokeMusicas";
    private static final int VERSAO_DB = 2;
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
        builderQuery.append("   idiomaNormalizado VARCHAR(100) NOT NULL ");
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
     * @param db
     */
    public void inserirValoresIniciais(SQLiteDatabase db) {
        try {
            AssetManager assetManager= context.getAssets();
            InputStreamReader is = new InputStreamReader(assetManager.open("musicas.csv"), "UTF-8");
            BufferedReader reader = new BufferedReader(is);
            String linha;

            while ((linha=reader.readLine()) != null) {
                String[] dadosDaLinha = linha.split(";");

                ContentValues valores = new ContentValues();
                valores.put("id", Integer.parseInt(dadosDaLinha[0]));
                valores.put("interprete", dadosDaLinha[1]);
                valores.put("titulo", dadosDaLinha[2]);
                valores.put("inicioLetra", dadosDaLinha[3]);
                valores.put("idioma", dadosDaLinha[4]);
                valores.put("interpreteNormalizado", StringUtils.stripAccents(dadosDaLinha[1]).toLowerCase());
                valores.put("tituloNormalizado", StringUtils.stripAccents(dadosDaLinha[2]).toLowerCase());
                valores.put("inicioLetraNormalizado", StringUtils.stripAccents(dadosDaLinha[3]).toLowerCase());
                valores.put("idiomaNormalizado", StringUtils.stripAccents(dadosDaLinha[4]).toLowerCase());

                db.insert("Musicas", null, valores);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
