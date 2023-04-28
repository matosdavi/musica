package br.com.ada.musica.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MusicaDTO {

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido.")
    private String cep;

    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 250, message = "O nome da música tem que ter no mínimo 3 caracteres.")
    private String nome;

    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 250, message = "O nome do artista tem que ter no mínimo 3 caracteres.")
    private String artista;

    @NotNull(message = "O gênero da música é obrigatório.")
    private GeneroDTO genero;

    public String getCep() {
        return cep;
    }

    public MusicaDTO setCep(String cep) {
        this.cep = cep;
        return this;
    }

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