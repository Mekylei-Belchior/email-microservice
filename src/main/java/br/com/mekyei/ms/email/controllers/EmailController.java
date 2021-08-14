package br.com.mekyei.ms.email.controllers;

import br.com.mekyei.ms.email.dtos.EmailDto;
import br.com.mekyei.ms.email.models.EmailModel;
import br.com.mekyei.ms.email.services.EmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "E-mail")
public class EmailController {

    @Autowired
    EmailService emailService;

    /**
     * Intercepta requisições POST enviadas para a URI mapeada e processa o envio do e-mail.
     *
     * @param dadosEmail dados necessário para criar um e-mail.
     * @return a resposta para o cliente com os dados do e-mail, status de envio e criação.
     */
    @PostMapping("envia-email")
    public ResponseEntity<EmailModel> enviaEmail(@RequestBody @Valid EmailDto dadosEmail) {
        /* Converte o EmailDto para EmailModel */
        EmailModel email = dadosEmail.toEmail();
        /* Envia o e-mail */
        emailService.enviarEmail(email);

        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }

}
