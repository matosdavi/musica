package br.com.ada.musica.controller;

import br.com.ada.musica.dto.FactoryDTO;
import br.com.ada.musica.dto.GeneroDTO;
import br.com.ada.musica.dto.ResultadoDTO;
import br.com.ada.musica.service.GeneroService;
import br.com.ada.musica.service.exception.GeneroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController extends BaseController {

    private GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<GeneroDTO>> listar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FactoryDTO.generosToDTO(generoService.listar()));
    }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody @Valid GeneroDTO generoDTO) {
        generoService.criar(FactoryDTO.dtoToEntity(generoDTO));
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
        try {
            generoService.editar(nomeFilter, FactoryDTO.dtoToEntity(generoDTO));
        } catch (GeneroNotFoundException ge) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem(ge.getMessage())
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