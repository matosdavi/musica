package br.com.ada.musica.service;

import br.com.ada.musica.dto.MusicaDTO;
import br.com.ada.musica.model.Musica;
import br.com.ada.musica.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MusicaService {

    @Autowired
    MusicaRepository musicaRepository;
    @Autowired
    GeneroService generoService;

    public  List<Musica> listar() {
        return musicaRepository.findWithGeneros();
    }

    public void criar(Musica musica) {
        musica.setGenero(
                generoService.getByUid(musica.getGenero().getUid())
        );
        musica.setUid(UUID.randomUUID().toString());
        musicaRepository.saveAndFlush(musica);
    }
}