package br.com.ada.musica.service;

import br.com.ada.musica.dto.GeneroDTO;
import br.com.ada.musica.service.exception.GeneroNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneroService {

    private List<GeneroDTO> generos = new ArrayList<>();

    public List<GeneroDTO> lista() {
        return generos;
    }

    public void criar(GeneroDTO generoDTO) {
        generos.add(generoDTO);
    }

    public void editar(String nomeFilter, GeneroDTO generoDTO) throws GeneroNotFoundException {
        GeneroDTO generoEncontrado = getByNome(nomeFilter);
        if (generoEncontrado == null) {
            throw new GeneroNotFoundException("O gênero " + nomeFilter + " não foi encontrado.");
        }
        generoEncontrado.setNome(generoDTO.getNome());
    }

    public boolean deletar(String nomeFilter) {
        GeneroDTO generoEncontrado = getByNome(nomeFilter);
        if (generoEncontrado == null) {
            return false;
        }
        generos.remove(generoEncontrado);
        return true;
    }

    public GeneroDTO getByNome(String nomeFilter) {
        GeneroDTO generoEncontrado = null;
        for (GeneroDTO generoLista : generos) {
            if (generoLista.getNome().equalsIgnoreCase(nomeFilter)) {
                generoEncontrado = generoLista;
                break;
            }
        }
        return generoEncontrado;
    }

    public boolean contains(String nomeFilter) {
        return getByNome(nomeFilter) != null;
    }
}