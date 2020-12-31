package br.com.karaokemusicas.service.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MusicaDTO implements Serializable {

    @SerializedName("codigo")
    private Integer codigo;

    @SerializedName("interprete")
    private String interprete;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("inicio_letra")
    private String inicioLetra;

    @SerializedName("idioma")
    private String idioma;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInicioLetra() {
        return inicioLetra;
    }

    public void setInicioLetra(String inicioLetra) {
        this.inicioLetra = inicioLetra;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

}
