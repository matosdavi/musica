package br.com.ada.musica.controller;

import br.com.ada.musica.dto.FactoryDTO;
import br.com.ada.musica.dto.MusicaDTO;
import br.com.ada.musica.dto.ResultadoDTO;
import br.com.ada.musica.service.ArtistaService;
import br.com.ada.musica.service.GeneroService;
import br.com.ada.musica.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/musica")
public class MusicaController extends BaseController {

    @Autowired
    private MusicaService musicaService;
    @Autowired
    private GeneroService generoService;
    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/lista")
    public List<MusicaDTO> listar(@RequestParam String filter) {
        if (filter != null && !filter.isEmpty()) {
            //return musicaService.filterByName(filter);
        }
        return FactoryDTO.musicasToDTO(musicaService.listar());
    }

    @GetMapping
    public MusicaDTO detalhar(@RequestParam String nome) {
        return MusicaDTO.builder().nome(nome).build();
        }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody @Valid MusicaDTO musicaDTO) {
        System.out.println("Nome da música: " + musicaDTO.getNome());
        musicaService.criar(FactoryDTO.dtoToEntity(musicaDTO));
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
        /**for (int i = musicaDTOs.size() - 1; i >= 0; i--){
            MusicaDTO musicaDTO = musicaDTOs.get(i);
            if (musicaDTO.getNome().equalsIgnoreCase(nome)){
                musicaDTOs.remove(i);
                removido++;
            }
        } */
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