package br.com.ada.maventeste.musica.dto;

public class MusicaDTO {

    private String nome;
    private String genero;

    public String getNome() {
        return nome;
    }

    public MusicaDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getGenero() {
        return genero;
    }

    public MusicaDTO setGenero(String genero) {
        this.genero = genero;
        return this;
    }
}
