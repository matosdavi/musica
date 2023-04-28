<<<<<<< HEAD:src/main/java/br/com/ada/musica/dto/ResultadoDTO.java
package br.com.ada.musica.dto;
=======
package br.com.ada.maventeste.musica.dto;
>>>>>>> origin/master:src/main/java/br/com/ada/maventeste/musica/dto/ResultadoDTO.java

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
