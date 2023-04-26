package br.com.ada.maventeste.musica.controller;

import br.com.ada.maventeste.musica.dto.GeneroDTO;
import br.com.ada.maventeste.musica.dto.ResultadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private List<GeneroDTO> generos = new ArrayList<>();

    @GetMapping("/lista")
    public ResponseEntity<List<GeneroDTO>> lista() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(generos);
    }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody GeneroDTO generoDTO) {
        if (generoDTO.getNome() == null || generoDTO.getNome().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O nome é obrigatório.")
                    );
        }
        generos.add(generoDTO);
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O gênero de música foi criado com sucesso.")
        );
    }
    @PutMapping
    public ResponseEntity<ResultadoDTO> editar(@RequestParam(name = "nome") String nomeFilter, @RequestBody GeneroDTO generoDTO) {
        GeneroDTO generoEncontrado = null;
        for (GeneroDTO generoLista : generos) {
            if (generoLista.getNome().equalsIgnoreCase(nomeFilter)) {
                generoEncontrado = generoLista;
                break;
            }
        }
        if (generoEncontrado == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O Gênero " + nomeFilter + " não foi encontrado.")
                    );
        }
        if (generoDTO.getNome() == null || generoDTO.getNome().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O nome é obrigatório.")
                    );
        }
        generoEncontrado.setNome(generoDTO.getNome());
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O gênero de música " + nomeFilter + "foi editado com sucesso.")
        );
    }

    @DeleteMapping
    public ResponseEntity<ResultadoDTO> deletar(@RequestParam(name = "nome") String nomeFilter) {
        GeneroDTO generoEncontrado = null;
        for (GeneroDTO generoLista : generos) {
            if (generoLista.getNome().equalsIgnoreCase(nomeFilter)) {
                generoEncontrado = generoLista;
                break;
            }
        }
        if (generoEncontrado == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O gênero " + nomeFilter + " não foi encontrado.")
                    );
        }
        generos.remove(generoEncontrado);
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O Gênero de música " + nomeFilter + " foi removido com sucesso.")
        );
    }
}