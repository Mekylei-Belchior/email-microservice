package br.com.mekyei.ms.email.models;

import br.com.mekyei.ms.email.enums.StatusEmail;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "emails")
public class EmailModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    private String proprietario;
    private String remetente;
    private String destinatario;
    private String assunto;

    @Lob
    private String conteudo;
    private LocalDateTime dataEnvio;
    private StatusEmail status;

    public EmailModel() {
    }

    public EmailModel(
            String proprietario,
            String remetente,
            String destinatario,
            String assunto,
            String conteudo) {

        this.proprietario = proprietario;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public StatusEmail getStatus() {
        return status;
    }

    public void setStatus(StatusEmail status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailModel email = (EmailModel) o;
        return id == email.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", destinatario='" + destinatario + '\'' +
                ", remetente='" + remetente + '\'' +
                ", assunto='" + assunto + '\'' +
                ", status=" + status +
                '}';
    }
}
