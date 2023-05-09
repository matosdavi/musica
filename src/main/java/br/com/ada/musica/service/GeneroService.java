package br.com.ada.musica.service;

import br.com.ada.musica.dto.GeneroDTO;
import br.com.ada.musica.model.Genero;
import br.com.ada.musica.repository.GeneroRepository;
import br.com.ada.musica.service.exception.GeneroNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    public void criar(Genero genero) {
       genero.setUid(UUID.randomUUID().toString());
       generoRepository.saveAndFlush(genero);
    }

    public void editar(String uid, Genero genero) throws GeneroNotFoundException {
        List<Genero> generos = generoRepository.findByUid(uid);
        if (generos.size() == 1) {
            Genero generoDB = generos.get(0);
            generoDB.setNome(genero.getNome());
        } else {
            throw new GeneroNotFoundException("O gênero " + uid + " não foi encontrado.");
        }
    }

    public boolean deletar(String uid) {
        List<Genero> generos = generoRepository.findByUid(uid);
        if (generos.size() == 0) {
            return false;
        }
        Genero genero = generos.get(0);
        generoRepository.delete(genero);
        return true;
    }

    public Genero getByUid(String uid) {
        List<Genero> generos = generoRepository.findByUid(uid);
        if (generos.size() == 1) {
            return generos.get(0);
        }
        return null;
    }
}