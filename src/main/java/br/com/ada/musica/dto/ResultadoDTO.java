package br.com.ada.musica.dto;
public class ResultadoDTO {

    private boolean resultado;
    private String mensagem;

    public boolean isResultado() {
        return resultado;
    }

    public ResultadoDTO setResultado(boolean resultado) {
        this.resultado = resultado;
        return this;
    }

    public String getMensagem() {
        return mensagem;
    }

    public ResultadoDTO setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }
}
