package br.com.ada.musica.repository;

import br.com.ada.musica.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT a FROM Artista a WHERE a.uid = :uid")
    List<Artista> findByUid(@Param("uid") String uid);
}