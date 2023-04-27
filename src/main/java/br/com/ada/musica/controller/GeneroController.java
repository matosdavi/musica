package br.com.ada.musica.controller;

import br.com.ada.musica.dto.GeneroDTO;
import br.com.ada.musica.dto.ResultadoDTO;
import br.com.ada.musica.service.GeneroService;
import br.com.ada.musica.service.exception.GeneroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<GeneroDTO>> lista() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generoService.lista()
                );
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
        generoService.criar(generoDTO);
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O gênero de música foi criado com sucesso.")
        );
    }
    @PutMapping
    public ResponseEntity<ResultadoDTO> editar(
            @RequestParam(name = "nome") String nomeFilter,
            @RequestBody GeneroDTO generoDTO
    ) {
        if (generoDTO.getNome() == null || generoDTO.getNome().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O nome é obrigatório.")
                    );
        }
        try {
            generoService.editar(nomeFilter, generoDTO);
        } catch (GeneroNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem(e.getMessage())
                    );
        }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O gênero de música " + nomeFilter + "foi editado com sucesso.")
        );
    }

    @DeleteMapping
    public ResponseEntity<ResultadoDTO> deletar(
            @RequestParam(name = "nome") String nomeFilter
    ) {
        boolean resultado = generoService.deletar(nomeFilter);
        if (resultado == false) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O gênero " + nomeFilter + " não foi encontrado.")
                    );
        }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O Gênero de música " + nomeFilter + " foi removido com sucesso.")
        );
    }
}