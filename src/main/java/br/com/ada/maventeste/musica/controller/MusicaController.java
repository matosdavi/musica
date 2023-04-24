package br.com.ada.maventeste.musica.controller;

import br.com.ada.maventeste.musica.dto.MusicaDTO;
import br.com.ada.maventeste.musica.dto.ResultadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    private List<MusicaDTO> musicaDTOs = new ArrayList<>();

    @GetMapping("/detalhe")
        public List<MusicaDTO> lista(@RequestParam String filter) {
            List<MusicaDTO> listaFiltrada = new ArrayList<>();
            for (MusicaDTO musicaDTO : musicaDTOs({
            if (musicaDTO.setNome().contains(filter)){
                listaFiltrada.add(musicaDTO);
            }
}
            return listaFiltrada;
        }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody MusicaDTO musicaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResultadoDTO().setResultado(true).setMensagem("funcionou!"));

    }

    @PutMapping
    public ResponseEntity<ResultadoDTO> editar(@RequestBody MusicaDTO musicaDTO) {

        Map<String, String> musicas = new HashMap<>();

        musicas.putAll(dados);

        return "Música " + dados.get("nome") + " editada com sucesso!";
    }

    @DeleteMapping
    public String deletar(@RequestParam String nome) {
        return "Música " + nome + " deletada com sucesso!";
    }
}