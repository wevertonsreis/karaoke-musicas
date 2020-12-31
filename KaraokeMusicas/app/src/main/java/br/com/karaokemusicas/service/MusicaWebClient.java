package br.com.karaokemusicas.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

import br.com.karaokemusicas.service.dto.ListaMusicasDTO;
import br.com.karaokemusicas.util.LocalDateTimeDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MusicaWebClient {

    private static final String URL_BASE = "https://raw.githubusercontent.com/wevertonsreis";
    private static final String URL_MUSICAS = "/karaoke-musicas-dados/master/dados/musicas.json";
    private static final String URL_ULTIMAS_MUSICAS = "/karaoke-musicas-dados/master/dados/ultimas-musicas.json";

    public ListaMusicasDTO buscarMusicas() {
        try {
            Request request = new Request.Builder()
                    .url(URL_BASE + URL_MUSICAS)
                    .build();

            Response response = new OkHttpClient().newCall(request).execute();

            return fromJson(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ListaMusicasDTO buscarUltimasMusicas() {
        try {
            Request request = new Request.Builder()
                    .url(URL_BASE + URL_ULTIMAS_MUSICAS)
                    .build();

            Response response = new OkHttpClient().newCall(request).execute();

            return fromJson(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ListaMusicasDTO fromJson(String json) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();

        return gson.fromJson(json, ListaMusicasDTO.class);
    }

}