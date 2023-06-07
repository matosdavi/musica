package br.com.ada.musica.dto;

import br.com.ada.musica.model.Artista;
import br.com.ada.musica.model.Genero;
import br.com.ada.musica.model.Musica;

import java.util.ArrayList;
import java.util.List;

public class FactoryDTO {

    public static ArtistaDTO entityToDTO(Artista artista) {
        if (artista == null) {
            return null;
        }
        return ArtistaDTO.builder()
                .uid(artista.getUid())
                .nome(artista.getNome())
                .build();
    }

    public static Artista dtoToEntity(ArtistaDTO artistaDTO) {
        if (artistaDTO == null) {
            return null;
        }
        Artista artista = new Artista();
        artista.setUid(artistaDTO.getUid());
        artista.setNome(artistaDTO.getNome());
        return artista;
    }

    public static List<ArtistaDTO> artistasToDTO(List<Artista> artistas) {
        List<ArtistaDTO> generosDTO = new ArrayList<>();
        for (Artista artista : artistas) {
            generosDTO.add(
                    FactoryDTO.entityToDTO(artista)
            );
        }
        return generosDTO;
    }
    public static GeneroDTO entityToDTO(Genero genero) {
        if (genero == null) {
            return null;
        }
        return GeneroDTO.builder()
                .uid(genero.getUid())
                .nome(genero.getNome())
                .build();
    }

    public static Genero dtoToEntity(GeneroDTO generoDTO) {
        if (generoDTO == null) {
            return null;
        }
        Genero genero = new Genero();
        genero.setUid(generoDTO.getUid());
        genero.setNome(generoDTO.getNome());
        return genero;
    }

    public static List<GeneroDTO> generosToDTO(List<Genero> generos) {
        List<GeneroDTO> generosDTO = new ArrayList<>();
        for (Genero genero : generos) {
            generosDTO.add(
                    FactoryDTO.entityToDTO(genero)
            );
        }
        return generosDTO;
    }

    public static MusicaDTO entityToDTO(Musica musica) {
        if (musica == null) {
            return null;
        }
        return MusicaDTO.builder()
                .uid(musica.getUid())
                .nome(musica.getNome())
                .artista(entityToDTO(musica.getArtista()))
                .genero(entityToDTO(musica.getGenero()))
                .build();
    }

    public static Musica dtoToEntity(MusicaDTO musicaDTO) {
        if (musicaDTO == null) {
            return null;
        }
        Musica musica = new Musica();
        musica.setUid(musicaDTO.getUid());
        musica.setNome(musicaDTO.getNome());
        if (musicaDTO.getGenero() != null) {
            musica.setGenero(dtoToEntity(musicaDTO.getGenero()));
        } if (musicaDTO.getArtista() != null) {
            musica.setArtista(dtoToEntity(musicaDTO.getArtista()));
        }
        return musica;
    }

    public static List<MusicaDTO> musicasToDTO(List<Musica> musicas) {
        List<MusicaDTO> musicasDTO = new ArrayList<>();
        for (Musica musica : musicas) {
            musicasDTO.add(
                    FactoryDTO.entityToDTO(musica)
            );
        }
        return musicasDTO;
    }
}