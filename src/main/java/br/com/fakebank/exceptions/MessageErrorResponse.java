package br.com.fakebank.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class MessageErrorResponse {

    private LocalDateTime dataHora;
    private String titulo;
    private String message;
    private List<MessageErrorDetail> messages;
    
    public MessageErrorResponse() {
        this.dataHora = LocalDateTime.now();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<MessageErrorDetail> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageErrorDetail> messages) {
        this.messages = messages;
    }
}
