package br.com.ada.musica.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ArtistaDTO {
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, message = "O nome tem que ter no mínimo 3 letras.")
    private String nome;

    public String getNome() {
        return nome;
    }

    public ArtistaDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }
}