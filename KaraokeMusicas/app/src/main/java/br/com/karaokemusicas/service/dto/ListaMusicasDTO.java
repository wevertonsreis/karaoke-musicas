package br.com.karaokemusicas.service.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ListaMusicasDTO implements Serializable {

    @SerializedName("data_alteracao")
    private LocalDateTime dataAlteracao;

    @SerializedName("musicas")
    private MusicaDTO[] musicas;

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public MusicaDTO[] getMusicas() {
        return musicas;
    }

    public void setMusicas(MusicaDTO[] musicas) {
        this.musicas = musicas;
    }

}