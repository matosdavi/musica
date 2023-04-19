package br.com.ada.maventeste.musica.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    @GetMapping("/detalhe")
    public Map<String, String> detalhe(@RequestParam String nome) {
        return new HashMap<>() {{
            put("nome", nome);
            put("genero", "genero");
        }};
    }

    @PostMapping
    public String criar(@RequestBody Map<String, String> dados) {

        Map<String, String> musicas = new HashMap<>();

        musicas.putAll(dados);

        return "Música " + musicas.get("nome") + " criada com sucesso!";
    }

    @PutMapping
    public String editar(@RequestBody Map<String, String> dados) {

        Map<String, String> musicas = new HashMap<>();

        musicas.putAll(dados);

        return "Música " + dados.get("nome") + " editada com sucesso!";
    }

    @DeleteMapping
    public String deletar(@RequestParam String nome) {
        return "Música " + nome + " deletada com sucesso!";
    }
}