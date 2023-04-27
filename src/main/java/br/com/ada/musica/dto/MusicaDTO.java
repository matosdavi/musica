package br.com.ada.musica.dto;

public class MusicaDTO {

    private String nome;
    private String artista;
    private GeneroDTO genero;

    public String getNome() {
        return nome;
    }

    public MusicaDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getArtista() {
        return artista;
    }

    public MusicaDTO setArtista(String artista) {
        this.artista = artista;
        return this;
    }

    public GeneroDTO getGenero() {
        return genero;
    }

    public MusicaDTO setGenero(GeneroDTO genero) {
        this.genero = genero;
        return this;
    }
}
