package br.com.mekyei.ms.email.consumers;

import br.com.mekyei.ms.email.dtos.EmailDto;
import br.com.mekyei.ms.email.models.EmailModel;
import br.com.mekyei.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    /**
     * Este método monitora a fila definida no RabbitListener e utiliza o exchange default.
     *
     * @param dadosEmail Os dados da mensagem.
     */
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void listen(@Payload EmailDto dadosEmail) {
        EmailModel emailModel = dadosEmail.toEmail();
        emailService.enviarEmail(emailModel);
    }

}
