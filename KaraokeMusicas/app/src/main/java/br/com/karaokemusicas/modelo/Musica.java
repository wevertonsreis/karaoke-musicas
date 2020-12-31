package br.com.karaokemusicas.modelo;

import java.time.LocalDateTime;

public class Musica extends Modelo {

    private String interprete;
    private String titulo;
    private String inicioLetra;
    private String idioma;
    private String interpreteNormalizado;
    private String tituloNormalizado;
    private String inicioLetraNormalizado;
    private String idiomaNormalizado;
    private Boolean favorita;
    private LocalDateTime dataAtualizacao;

    public Musica() {}

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

    public String getInterpreteNormalizado() {
        return interpreteNormalizado;
    }

    public void setInterpreteNormalizado(String interpreteNormalizado) {
        this.interpreteNormalizado = interpreteNormalizado;
    }

    public String getTituloNormalizado() {
        return tituloNormalizado;
    }

    public void setTituloNormalizado(String tituloNormalizado) {
        this.tituloNormalizado = tituloNormalizado;
    }

    public String getInicioLetraNormalizado() {
        return inicioLetraNormalizado;
    }

    public void setInicioLetraNormalizado(String inicioLetraNormalizado) {
        this.inicioLetraNormalizado = inicioLetraNormalizado;
    }

    public String getIdiomaNormalizado() {
        return idiomaNormalizado;
    }

    public void setIdiomaNormalizado(String idiomaNormalizado) {
        this.idiomaNormalizado = idiomaNormalizado;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}