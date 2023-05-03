package br.com.ada.musica.service;

import br.com.ada.musica.dto.ArtistaDTO;
import br.com.ada.musica.service.exception.ArtistaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistaService {
    private List<ArtistaDTO> artistas = new ArrayList<>();

    public List<ArtistaDTO> listar() {
        return artistas;
    }

    public void criar(ArtistaDTO artistaDTO) {
        artistas.add(artistaDTO);
    }

    public void editar(String nomeFilter, ArtistaDTO artistaDTO) throws ArtistaNotFoundException {
        ArtistaDTO artistaEncontrado = getByNome(nomeFilter);
        if (artistaEncontrado == null) {
            throw new ArtistaNotFoundException("O gênero " + nomeFilter + " não foi encontrado.");
        }
        artistaEncontrado.setNome(artistaDTO.getNome());
    }

    public boolean deletar(String nomeFilter) {
        ArtistaDTO artistaEncontrado = getByNome(nomeFilter);
        if (artistaEncontrado == null) {
            return false;
        }
        artistas.remove(artistaEncontrado);
        return true;
    }

    public ArtistaDTO getByNome(String nomeFilter) {
        ArtistaDTO artistaEncontrado = null;
        for (ArtistaDTO artistaLista : artistas) {
            if (artistaLista.getNome().equalsIgnoreCase(nomeFilter)) {
                artistaEncontrado = artistaLista;
                break;
            }
        }
        return artistaEncontrado;
    }

    public boolean contains(String nomeFilter) {
        return getByNome(nomeFilter) != null;
    }
}