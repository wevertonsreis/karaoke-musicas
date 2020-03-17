package br.com.karaokemusicas.modelo;

public class Musica extends Modelo {

    private String interprete;
    private String titulo;
    private String inicioLetra;
    private String idioma;

    public Musica(Long id, String interprete, String titulo, String inicioLetra) {
        setId(id);
        this.interprete = interprete;
        this.titulo = titulo;
        this.inicioLetra = inicioLetra;
    }

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
}
