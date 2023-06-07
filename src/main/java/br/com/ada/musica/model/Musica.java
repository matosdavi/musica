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
    @ManyToOne(fetch = FetchType.LAZY)
    private Genero genero;
    @ManyToOne(fetch = FetchType.LAZY)
    private Artista artista;
}