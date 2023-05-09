package br.com.ada.musica.repository;

import br.com.ada.musica.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query("SELECT g FROM Genero g WHERE g.uid = :uid")
    List<Genero> findByUid(@Param("uid") String uid);
}