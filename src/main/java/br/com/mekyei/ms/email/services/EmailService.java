package br.com.mekyei.ms.email.services;

import br.com.mekyei.ms.email.enums.StatusEmail;
import br.com.mekyei.ms.email.models.EmailModel;
import br.com.mekyei.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Envia o email e/ou realiza a persistência no Banco de Dados (BD).
     * @param email objeto que possui os dados da mensagem.
     */
    public void enviarEmail(EmailModel email) {
        /* Atribui a data de envio da mensagem */
        email.setDataEnvio(LocalDateTime.now());

        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();

            /* Prepara a mensagem para o envio */
            mensagem.setFrom(email.getRemetente());
            mensagem.setTo(email.getDestinatario());
            mensagem.setSubject(email.getAssunto());
            mensagem.setText(email.getConteudo());

            /* Envia a mensagem */
            javaMailSender.send(mensagem);

            /* Atribui o Status de envio em caso de sucesso */
            email.setStatus(StatusEmail.ENVIADO);

        } catch (MailException ex) {
            /* Em caso de erro no envio do e-mail, atriui o Status correspondente */
            email.setStatus(StatusEmail.PROBLEMA);
        } finally {
            /* Em caso de sucesso ou falha, persiste a mensagem no BD */
            emailRepository.save(email);
        }
    }
}
