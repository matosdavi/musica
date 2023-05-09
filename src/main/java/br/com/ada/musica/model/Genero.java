package br.com.ada.musica.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="genero")
@Data
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
    private Collection<Musica> musicas = new ArrayList<>();
}