package br.com.ada.maventeste.musica.controller;

import br.com.ada.maventeste.musica.dto.MusicaDTO;
import br.com.ada.maventeste.musica.dto.ResultadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musica")
public class MusicaController {

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
                .setGenero("trap");
        }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody MusicaDTO musicaDTO) {
        if (musicaDTO.getGenero() == null || musicaDTO.getGenero().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResultadoDTO()
                            .setResultado(false)
                            .setMensagem("Música de gênero " + musicaDTO.getGenero() + " é inválida.")
            );
        }
        if (musicaDTO.getNome() == null || musicaDTO.getNome().isEmpty()) {
            ResultadoDTO resultadoDTO = new ResultadoDTO()
                    .setResultado(false)
                    .setMensagem("Música de nome " + musicaDTO.getNome() + " é inválida.");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(resultadoDTO);
        }
        System.out.println("Nome da música: " + musicaDTO.getNome());
        musicaDTOs.add(musicaDTO);
        return ResponseEntity.ok(new ResultadoDTO()
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