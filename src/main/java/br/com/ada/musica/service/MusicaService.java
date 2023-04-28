package br.com.ada.musica.service;

import br.com.ada.musica.dto.MusicaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicaService {

    private List<MusicaDTO> musicas = new ArrayList<>();

    public  List<MusicaDTO> listar() {
        return musicas;
    }

    public List<MusicaDTO> filterByName(String filter) {
        List<MusicaDTO> listaFiltrada = new ArrayList<>();
        for (MusicaDTO musicaDTO: musicas) {
            if (musicaDTO.getNome().toLowerCase().contains(filter.toLowerCase())) {
                listaFiltrada.add(musicaDTO);
            }
        }
        return listaFiltrada;
    }
}