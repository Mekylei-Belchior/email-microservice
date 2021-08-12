package br.com.mekyei.ms.email.dtos;

import br.com.mekyei.ms.email.models.EmailModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDto {

    @NotBlank
    private String proprietario;
    @NotBlank
    @Email
    private String remetente;
    @NotBlank
    @Email
    private String destinatario;
    @NotBlank
    private String assunto;
    @NotBlank
    private String conteudo;

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
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

    /**
     * Converte objeto para EmailModel.
     * @return objeto EmailModel.
     */
    public EmailModel toEmail() {
        return new EmailModel(
                this.proprietario,
                this.remetente,
                this.destinatario,
                this.assunto,
                this.conteudo
        );
    }
}
