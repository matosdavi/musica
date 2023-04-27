package br.com.ada.musica.controller;

import br.com.ada.musica.dto.MusicaDTO;
import br.com.ada.musica.dto.ResultadoDTO;
import br.com.ada.musica.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    @Autowired
    private GeneroService generoService;

    private List<MusicaDTO> musicaDTOs = new ArrayList<>();

    @GetMapping("/lista")
    public List<MusicaDTO> lista(@RequestParam String filter) {

        if (filter != null && !filter.isEmpty()) {
            List<MusicaDTO> listaFiltrada = new ArrayList<>();
            for (MusicaDTO musicaDTO : musicaDTOs) {
                if (musicaDTO.getNome().toLowerCase().contains(filter.toLowerCase())) {
                    listaFiltrada.add(musicaDTO);
                }
            }
            return listaFiltrada;
        }
        return musicaDTOs;
    }

    @GetMapping
    public MusicaDTO detalhe(@RequestParam String nome){
        return new MusicaDTO()
                .setNome(nome)
                .setArtista("trap");
        }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody MusicaDTO musicaDTO) {
        if (musicaDTO.getArtista() == null || musicaDTO.getArtista().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResultadoDTO()
                            .setResultado(false)
                            .setMensagem("Música de artista " + musicaDTO.getArtista() + " é inválida.")
            );
        }
        if (musicaDTO.getNome() == null || musicaDTO.getNome().isEmpty()) {
            ResultadoDTO resultadoDTO = new ResultadoDTO()
                    .setResultado(false)
                    .setMensagem("Música de nome " + musicaDTO.getNome() + " é inválida.");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    resultadoDTO);
        }
        if (musicaDTO.getGenero() == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResultadoDTO()
                            .setResultado(false)
                            .setMensagem("Música não tem o gênero definido.")
            );
        }
        if (!generoService.contains(musicaDTO.getGenero().getNome())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResultadoDTO()
                            .setResultado(false)
                            .setMensagem("O Gênero " + musicaDTO.getGenero().getNome() + "não existe.")
            );
        }
        System.out.println("Nome da música: " + musicaDTO.getNome());
        musicaDTOs.add(musicaDTO);
        return ResponseEntity.ok(
                new ResultadoDTO()
                .setResultado(true)
                .setMensagem("Música " + musicaDTO.getNome() + " criada com sucesso."));
    }

    @PutMapping
    public ResultadoDTO editar(@RequestBody MusicaDTO musicaDTO) {
        return new ResultadoDTO()
                .setResultado(true)
                .setMensagem("Música " + musicaDTO.getNome() + " editada com sucesso!");
    }

    @DeleteMapping
    public ResponseEntity<ResultadoDTO> deletar(@RequestParam String nome) {
        int removido = 0;
        for (int i = musicaDTOs.size() - 1; i >= 0; i--){
            MusicaDTO musicaDTO = musicaDTOs.get(i);
            if (musicaDTO.getNome().equalsIgnoreCase(nome)){
                musicaDTOs.remove(i);
                removido++;
            }
        }
        if (removido == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResultadoDTO()
                            .setResultado(false)
                            .setMensagem("Não foi encontrada nenhuma música com este nome.")
            );
        }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem(removido + " músicas com o nome " + nome + " foram removidas com sucesso.")
        );
    }
}