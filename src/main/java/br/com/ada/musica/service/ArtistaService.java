package br.com.ada.musica.service;

import br.com.ada.musica.model.Artista;
import br.com.ada.musica.repository.ArtistaRepository;
import br.com.ada.musica.service.exception.ArtistaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> listar() {
        return artistaRepository.findAll();
    }

    public void criar(Artista artista) {
        artista.setUid(UUID.randomUUID().toString());
        artistaRepository.saveAndFlush(artista);
    }

    public void editar(String uid, Artista artista) throws ArtistaNotFoundException {
        List<Artista> artistas = artistaRepository.findByUid(uid);
        if (artistas.size() == 1) {
            Artista artistaDB = artistas.get(0);
            artistaDB.setNome(artista.getNome());
            artistaRepository.saveAndFlush(artistaDB);
        } else {
            throw new ArtistaNotFoundException("O artista " + uid + " não foi encontrado.");
        }
    }

    public boolean deletar(String uid) {
        List<Artista> artistas = artistaRepository.findByUid(uid);
        if (artistas.size() == 0) {
            return false;
        }
        Artista artista = artistas.get(0);
        artistaRepository.delete(artista);
        return true;
    }

    public Artista getByUid(String uid) {
        List<Artista> artistas = artistaRepository.findByUid(uid);
        if (artistas.size() == 1) {
            return artistas.get(0);
        }
        return null;
    }
}