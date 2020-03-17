package br.com.karaokemusicas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DAOHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "KaraokeMusicas";
    private static final int VERSAO_DB = 1;
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
        builderQuery.append("   idioma VARCHAR(100) NOT NULL ");
        builderQuery.append("); ");

        db.execSQL(builderQuery.toString());

        inserirValoresIniciais(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder builderQuery = new StringBuilder();
        builderQuery.append("DROP TABLE IF EXISTS Musicas;");
        db.execSQL(builderQuery.toString());
        onCreate(db);
    }

    /**
     *
     * @param db
     */
    private void inserirValoresIniciais(SQLiteDatabase db) {
        try {
            AssetManager assetManager= context.getAssets();
            InputStreamReader is = new InputStreamReader(assetManager.open("musicas-nacionais.csv"), "UTF-8");
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

                db.insert("Musicas", null, valores);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
