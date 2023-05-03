package br.com.ada.musica.service.exception;

public class ArtistaNotFoundException extends Exception {

    public ArtistaNotFoundException(String mensagem) {
        super(mensagem);
    }
}