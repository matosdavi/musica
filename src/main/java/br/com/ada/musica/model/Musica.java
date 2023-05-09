package br.com.ada.musica.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="musica")
@Data
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String nome;
    private String artista;
    @ManyToOne(fetch = FetchType.LAZY)
    private Genero genero;
}