package br.com.ada.musica.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class MusicaDTO {

    private String uid;
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    @NotBlank(message = "O artista é obrigatório.")
    @Length(min = 3, max = 250, message = "O nome da música tem que ter no mínimo 3 caracteres.")
    private ArtistaDTO artista;
    @NotNull(message = "O gênero da música é obrigatório.")
    @Length(min = 3, max = 250, message = "O nome da música tem que ter no mínimo 3 caracteres.")
    private GeneroDTO genero;
}