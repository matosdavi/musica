package br.com.ada.musica.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class GeneroDTO {

    private String uid;
    @NotBlank(message = "O nome do gênero é obrigatório.")
    @Length(min = 3, message = "O nome do gênero tem que ter no mínimo 3 letras.")
    private String nome;
}