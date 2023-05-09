package br.com.ada.musica.repository;

import br.com.ada.musica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    @Query("SELECT m FROM Musica m JOIN FETCH m.genero c")
    List<Musica> findWithGeneros();
}