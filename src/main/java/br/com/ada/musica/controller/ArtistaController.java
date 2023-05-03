package br.com.ada.musica.controller;

import br.com.ada.musica.dto.ArtistaDTO;
import br.com.ada.musica.dto.ResultadoDTO;

import br.com.ada.musica.service.ArtistaService;
import br.com.ada.musica.service.exception.ArtistaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/artista")
public class ArtistaController extends BaseController {

    private ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ArtistaDTO>> listar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        artistaService.listar()
                );
    }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody @Valid ArtistaDTO artistaDTO) {
        artistaService.criar(artistaDTO);
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O artista foi criado com sucesso.")
        );
    }

    @PutMapping
    public ResponseEntity<ResultadoDTO> editar(
            @RequestParam(name = "nome") String nomeFilter,
            @RequestBody ArtistaDTO artistaDTO
    ) {
        try {
            artistaService.editar(nomeFilter, artistaDTO);
        } catch (ArtistaNotFoundException ae) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem(ae.getMessage())
                    );
        }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O artista " + nomeFilter + " foi editado com sucesso.")
        );
    }

    @DeleteMapping
    public ResponseEntity<ResultadoDTO> deletar(
            @RequestParam(name = "nome") String nomeFilter
    ) {
        boolean resultado = artistaService.deletar(nomeFilter);
        if (resultado == false) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("O artista " + nomeFilter + " não foi encontrado.")
                    );
        }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("O artista " + nomeFilter + " foi removido com sucesso.")
        );
    }
}